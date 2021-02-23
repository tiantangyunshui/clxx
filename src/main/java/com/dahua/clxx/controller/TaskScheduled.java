package com.dahua.clxx.controller;

import com.dahua.clxx.service.CardPersonService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class TaskScheduled {

    @Resource
    private CardPersonService cardPersonService;

    /**
     * 移除失效token的定时任务
     */
    @Scheduled(cron = "${tokenCron}")
    public void removeUser(){
        cardPersonService.removeUser();
    }

    /**
     * 添加门禁权限定时任务
     */
    public void addPrivilige(){

    }

    /**
     * 移除门禁权限定时任务
     */
    public void removePrivilige(){

    }

}
