package com.dahua.clxx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dahua.clxx.pojo.Apply;
import com.dahua.clxx.pojo.ApplyStateDto;
import com.dahua.clxx.pojo.ApplyVo;
import com.dahua.clxx.service.ApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/* 类注解 */
@Api(value = "申请表")
@RestController
@Slf4j
@RequestMapping("apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public IPage<ApplyVo> queryApplyPage(@RequestBody Apply apply,@ApiParam(value = "页码" , required=true )@RequestParam("page") int page,@ApiParam(value = "每页条数" , required=true )@RequestParam("size") int size){
        return applyService.queryApplyPage(apply,page,size);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addApply(@RequestBody Apply apply){
        applyService.addApply(apply);
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    public void updApply(@RequestBody ApplyStateDto apply){
        applyService.updApply(apply);
    }

}
