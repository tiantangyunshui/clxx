package com.dahua.clxx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dahua.clxx.config.Result;
import com.dahua.clxx.pojo.*;
import com.dahua.clxx.service.ApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/* 类注解 */
@Api(value = "申请表")
@RestController
@Slf4j
@RequestMapping("apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<IPage<ApplyVo>> queryApplyPage(@RequestBody ClxxApplyDto apply, @ApiParam(value = "页码" , required=true )@RequestParam("page") int page, @ApiParam(value = "每页条数" , required=true )@RequestParam("size") int size){
        return new Result<>(applyService.queryApplyPage(apply,page,size));
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<ApplyVo> info(@RequestParam("id") String id){
        return new Result<>(applyService.queryApplyPage(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<String> addApply(@RequestBody ClxxApplyDto apply){
        applyService.addApply(apply);
        return new Result<>("");
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    public Result<String> updApply(@RequestBody ApplyStateDto apply){
        applyService.updApply(apply);
        return new Result<>("");
    }

    @GetMapping("/export")
    public Result<String> download(@ApiParam(value = "需要导出的id(多个id用\",\"分隔)" , required=true )@RequestParam("ids") String ids,HttpServletResponse response) {
        applyService.export(ids,response);
        return new Result<>("报错",500);
    }

}
