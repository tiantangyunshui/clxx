package com.dahua.clxx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.config.TokenMap;
import com.dahua.clxx.exception.ErrorUtil;
import com.dahua.clxx.mapper.ApplyMapper;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CardPersonServiceImpl implements CardPersonService {

    //获取公钥
    public static final String PUBLIC_KEY = "/WPMS/getPublicKey";
    //模拟登陆
    public static final String LOGIN = "/WPMS/login";
    //更新人脸图片
    private static final String PERSON_IMG = "/CardSolution/common/saveMobileBase64ImageToByte";

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
    public void addPrivilige() {
        //TODO 判断是否开卡，授权
    }

    @Override
    public void removePrivilige() {
        IPage<Apply> page = applyMapper.selectPage(new Page<>(1, 100),
                Wrappers.<Apply>lambdaQuery().eq(Apply::getTimeLeave,
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        List<Apply> list = page.getRecords();
        log.info("移除权限的列表：{}", list);
        //TODO 移除权限
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
                JSONObject tokenResultObject = JSON.parseObject(publicKeyResult);
                TokenMap.dssToken = tokenResultObject.getString("token");
            }
        } catch (Exception e) {
            log.info("刷新dsstoken报错：{}", ErrorUtil.err(e));
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Date());
//        login(null,80,"system","dahua2006");

    }

}
