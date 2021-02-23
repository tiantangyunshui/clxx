package com.dahua.clxx.service.impl;

import com.alibaba.fastjson.JSON;
import com.dahua.clxx.config.TokenMap;
import com.dahua.clxx.pojo.PersonFaceImgDto;
import com.dahua.clxx.pojo.PersonFaceImgVo;
import com.dahua.clxx.pojo.Token;
import com.dahua.clxx.service.CardPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class CardPersonServiceImpl implements CardPersonService {

    private static final String updPersonFaceUrl = "/CardSolution/common/saveMobileBase64ImageToByte";

    @Value("${dssIp}")
    private String dssIp;

    @Value("${used}")
    private long used;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void removeUser() {
        long t1 = System.currentTimeMillis();
        long dieSec = used * 1000;
        for (Map.Entry<String, Token> m : TokenMap.map.entrySet()) {
            if(t1-m.getValue().getTime()>dieSec){
                TokenMap.map.remove(m.getKey());
            }
        }
        log.info("定期清理失效的token：{}",TokenMap.map);
    }

    @Override
    public boolean updFaceImg(PersonFaceImgDto personFaceDto) {
        String s = restTemplate.postForObject("http://"+dssIp+updPersonFaceUrl,personFaceDto,String.class);
        log.info("人脸更新结果：{}",s);
        PersonFaceImgVo vo = JSON.parseObject(s, PersonFaceImgVo.class);
        assert vo != null;
        return vo.isSuccess();
    }
}
