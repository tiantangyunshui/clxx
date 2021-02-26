package com.dahua.clxx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.config.TokenMap;
import com.dahua.clxx.exception.ErrorUtil;
import com.dahua.clxx.mapper.ApplyMapper;
import com.dahua.clxx.mapper.UserMapper;
import com.dahua.clxx.pojo.*;
import com.dahua.clxx.service.CardPersonService;
import com.dahua.clxx.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CardPersonServiceImpl implements CardPersonService {

    //获取公钥
    public static final String PUBLIC_KEY = "/WPMS/getPublicKey";
    //模拟登陆
    public static final String LOGIN = "/WPMS/login";
    //更新人脸图片
    private static final String PERSON_IMG = "/CardSolution/common/saveMobileBase64ImageToByte";
    //开卡
    private static final String OPEN_CARD = "/CardSolution/card/card/open/batch";
    //授权
    private static final String PRIVILIGE_ADD = "/CardSolution/card/accessControl/doorAuthority/update";
    //删除权限
    private static final String PRIVILIGE_DEL = "/CardSolution/card/accessControl/doorAuthority/delete/batch";

    @Value("${dssIp}")
    private String dssIp;

    @Value("${dssName}")
    private String dssName;

    @Value("${dssPassword}")
    private String dssPassword;

    @Value("${used}")
    private long used;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void removeUser() {
        long t1 = System.currentTimeMillis();
        long dieSec = used * 1000;
        for (Map.Entry<String, Token> m : TokenMap.map.entrySet()) {
            if (t1 - m.getValue().getTime() > dieSec) {
                TokenMap.map.remove(m.getKey());
            }
        }
        log.info("定期清理失效的token：{}", TokenMap.map);
    }

    @Override
    public boolean updFaceImg(PersonFaceImgDto personFaceDto) {
        String s = restTemplate.postForObject("http://" + dssIp + PERSON_IMG, personFaceDto, String.class);
        log.info("人脸更新结果：{}", s);
        PersonFaceImgVo vo = JSON.parseObject(s, PersonFaceImgVo.class);
        assert vo != null;
        return vo.isSuccess();
    }

    @Override
    public void refreshDssToken() {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("loginName", dssName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
            String publicKeyResult = restTemplate.postForObject("http://" + dssIp + PUBLIC_KEY, httpEntity, String.class);
            log.info("publicKeyResult:{}",publicKeyResult);
            if ((publicKeyResult != null && !"".equals(publicKeyResult))) {
                JSONObject publicKeyResultObject = JSON.parseObject(publicKeyResult);
                String publicKey = publicKeyResultObject.getString("publicKey");
                String entryPassword = RSAUtils.encryptBASE64(RSAUtils.encryptByPublicKey(dssPassword.getBytes(), publicKey));
                paramMap.put("loginPass", entryPassword);
                httpEntity = new HttpEntity<>(paramMap, headers);
                String result = restTemplate.postForObject("http://" + dssIp + LOGIN, httpEntity, String.class);
                log.info("result:{}",result);
                JSONObject tokenResultObject = JSON.parseObject(result);
                TokenMap.dssToken = tokenResultObject.getString("token");
            }
        } catch (Exception e) {
            log.info("刷新dsstoken报错：{}", ErrorUtil.err(e));
        }
        log.info("============:{}",TokenMap.dssToken);
    }

    @Override
    public void addPrivilige() {
        //TODO 判断是否开卡，授权
        //查询当天需要授权的申请
        Wrapper<ClxxApply> wrapper = Wrappers.<ClxxApply>lambdaQuery().apply("date_format(time_back,'%Y-%m-%d') = '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"'");
        List<ClxxApply> list = new ArrayList<>(100);
        getApply(wrapper,1,list);
        List<PersonCard> cards = userMapper.getCard(list.stream().map(ClxxApply::getStudentId).collect(Collectors.toList()));
        List<Long> psersonCodes = cards.stream().map(PersonCard::getPersonCode).collect(Collectors.toList());
        //没有开过卡的学生，重新开卡
        List<ClxxApply> ll = list.stream().filter(a-> !psersonCodes.contains(a.getStudentId())).collect(Collectors.toList());
        openCard(ll);
        //授权
        cardPriviligeAdd(list);
    }

    @Override
    public void removePrivilige() {
        Wrapper<ClxxApply> wrapper = Wrappers.<ClxxApply>lambdaQuery().apply("date_format(time_leave,'%Y-%m-%d') = '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"'");
        List<ClxxApply> list = new ArrayList<>(100);
        getApply(wrapper,1,list);
        log.info("移除权限的列表：{}", list);
        //TODO 移除权限
        cardPriviligeDel(list);
    }

    /**
     * 获取申请列表
     */
    private void getApply(Wrapper<ClxxApply> wrapper, int page, List<ClxxApply> list){
        List<ClxxApply> record = applyMapper.selectPage(new Page<>(page, 2),wrapper).getRecords();
        list.addAll(record);
        if(record.size()>0){
            getApply(wrapper,page+1,list);
        }
    }

    /**
     * 给学生开卡
     */
    private void openCard(List<ClxxApply> list){
        List<Object> openCardList = new ArrayList<>();
        for (ClxxApply apply : list) {
            String cardNo = apply.getStudentId()+"";
            if(cardNo.length()<8){
                cardNo = "00000000".substring(0,8-cardNo.length())+cardNo;
            }else{
                cardNo = cardNo.substring(0,8);
            }
            Map<String, Object> openCardMap = new HashMap<>();
            openCardMap.put("category", "0");
            openCardMap.put("cardNumber", cardNo);
            openCardMap.put("cardType", "0");
            openCardMap.put("cardStatus", "ACTIVE");
            openCardMap.put("startDate", "2021-02-24");
            openCardMap.put("endDate", "2030-08-25");
            openCardMap.put("cardSubsidy", "0");
            openCardMap.put("cardCash", "0");
            openCardMap.put("cardCost", "0");
            openCardMap.put("cardDeposit", "0");
            openCardMap.put("subSystems", "1,3,4,5,6");
            openCardMap.put("personId", apply.getStudentId());
            openCardList.add(openCardMap);
        }
        Map<String, Object> openCardReq = new HashMap<>();
        openCardReq.put("objectList", openCardList);
        log.info("OPEN_CARD:{}",JSON.toJSON(openCardReq));
        String rst = restTemplate.postForObject("http://" + dssIp + OPEN_CARD + "?token=" + TokenMap.dssToken, openCardReq, String.class);
        log.info("开卡：{}",rst);
    }

    /**
     * 授权
     */
    private void cardPriviligeAdd(List<ClxxApply> list){
        for (ClxxApply clxxApply : list) {
            String cardNo = clxxApply.getStudentId()+"";
            if(cardNo.length()<8){
                cardNo = "00000000".substring(0,8-cardNo.length())+cardNo;
            }else{
                cardNo = cardNo.substring(0,8);
            }
            List<Object> cardPrivilegeDetails = new ArrayList<>();
            Map<String, Object> temp = new HashMap<>();
            temp.put("privilegeType", 2);
            temp.put("resouceCode", 1);
            cardPrivilegeDetails.add(temp);
            Map<String, Object> doorAuthority = new HashMap<>();
            doorAuthority.put("cardNumber", cardNo);
            doorAuthority.put("timeQuantumId", 1);
            doorAuthority.put("cardPrivilegeDetails", cardPrivilegeDetails);
            log.info("DOOR_AUTHORITY:{}",JSON.toJSON(doorAuthority));
            //调一卡通接口授权人脸下发
            String rst = restTemplate.postForObject("http://" + dssIp + PRIVILIGE_ADD + "?token=" + TokenMap.dssToken, doorAuthority, String.class);
            log.info("授权：{}",rst);
        }
    }


    /**
     * 删除门禁权限
     */
    private void cardPriviligeDel(List<ClxxApply> list){
        List<String> ll = new ArrayList<>();
        for (ClxxApply clxxApply : list) {
            String cardNo = clxxApply.getStudentId()+"";
            if(cardNo.length()<8){
                cardNo = "00000000".substring(0,8-cardNo.length())+cardNo;
            }else{
                cardNo = cardNo.substring(0,8);
            }
            ll.add(cardNo);
        }
        Map<String, Object> temp = new HashMap<>();
        temp.put("privilegeType", "3");
        temp.put("cardNums",ll);
        //调一卡通接口权限删除
        String rst = restTemplate.postForObject("http://" + dssIp + PRIVILIGE_DEL + "?token=" + TokenMap.dssToken, temp, String.class);
        log.info("删除权限：{}",rst);
    }
}
