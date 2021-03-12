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
    @Scheduled(cron = "${addPriviligeCron}")
    public void addPrivilige(){
        cardPersonService.addPrivilige();
    }

    /**
     * 移除门禁权限定时任务
     */
    @Scheduled(cron = "${removePriviligeCron}")
    public void removePrivilige(){
        cardPersonService.removePrivilige();
    }

    /**
     * 刷新dss Token定时任务
     */
    @Scheduled(fixedRate = 7200000)
    public void refreshDssToken() {
        cardPersonService.refreshDssToken();
    }
}
