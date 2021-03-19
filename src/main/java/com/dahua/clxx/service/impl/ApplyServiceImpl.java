package com.dahua.clxx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.exception.BusinessRuntimeException;
import com.dahua.clxx.exception.ErrorUtil;
import com.dahua.clxx.mapper.ApplyMapper;
import com.dahua.clxx.mapper.UserMapper;
import com.dahua.clxx.pojo.*;
import com.dahua.clxx.service.ApplyService;

import com.dahua.clxx.service.CardPersonService;
import com.dahua.clxx.util.ExportUtil;
import com.dahua.clxx.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

@Slf4j
@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CardPersonService cardPersonService;

    @Value("${dssIp}")
    private String dssIp;

    @Override
    public IPage<ApplyVo> queryApplyPage(ClxxApplyDto apply, int page, int size) {
        log.info("page:{}\nsize:{}\napply1:{}",page,size,JSON.toJSONString(apply));
        if("3".equals(apply.getState())){
            apply.setState(null);
            apply.setStateNull("0");
        }
        IPage<ApplyVo> result = applyMapper.queryApplyPage(new Page<>(page, size), apply);
        for (ApplyVo record : result.getRecords()) {
            if("1".equals(record.getType())){
                String temp = record.getTimeBack();
                record.setTimeBack(record.getTimeLeave());
                record.setTimeLeave(temp);
            }
        }
        return result;
    }

    @Override
    public void addApply(ClxxApplyDto apply) {
        if(apply.getBase64file()!=null && !"".equals(apply.getBase64file())){
            PersonFaceImgDto img = new PersonFaceImgDto();
            img.setPersonId(apply.getStudentId()+"");
            img.setBase64file(apply.getBase64file());
            cardPersonService.updFaceImg(img);
        }
        apply.setState("0");
        apply.setId(new SnowflakeIdWorker(0, 0).nextId()+"");
        applyMapper.insert(apply);
    }

    @Override
    public void updApply(ApplyStateDto applyDto) {
        ClxxApply apply = new ClxxApply();
        apply.setId(applyDto.getId());
        apply.setState(applyDto.getState());
        applyMapper.updateById(apply);
    }

    @Override
    public ApplyVo queryApplyPage(String id) {
        ClxxApplyDto apply = new ClxxApplyDto();
        apply.setId(id);
        IPage<ApplyVo> page = applyMapper.queryApplyPage(new Page<>(1, 1), apply);
        if(page.getRecords().size()==0){
            throw new BusinessRuntimeException(500, "数据不存在");
        }
        ApplyVo vo = page.getRecords().get(0);
        List<Person> list = userMapper.getStudent(vo.getStudentId());
        if(list.size()>0) {
            Person person = list.get(0);
            if (person.getFaceImg() != null) {
                vo.setFaceImg(person.getFaceImg().replace("serverIp",dssIp));
            }
        }
        return vo;
    }

    @Override
    public void export(String ids, HttpServletResponse response) {
        Map<String,String> map = new HashMap<>();
        map.put("0","未审核");
        map.put("1","通过");
        map.put("2","不通过");
        Map<String,String> map2 = new HashMap<>();
        map2.put("0","未下发");
        map2.put("1","下发成功");
        map2.put("2","下发失败");
        String fName = "审批导出";
        List<String> csvList = new ArrayList<>();
        ClxxApplyDto apply = new ClxxApplyDto();
        apply.setIds(ids);
        List<ApplyVo> list = applyMapper.queryApplyPage(new Page<>(1, 500), apply).getRecords();
        log.info("导出的数据：{}", JSON.toJSON(list));
        csvList.add("序号,申请学生学号,申请学生姓名,审批教师姓名,审批教师手机号,外出/返校,出校/返校时间,回校/离校时间,审批结果,权限下发状态");
        for (int i = 0; i < list.size(); i++) {
            ApplyVo vo = list.get(i);
            if("1".equals(vo.getType())){
                String temp = vo.getTimeBack();
                vo.setTimeBack(vo.getTimeLeave());
                vo.setTimeLeave(temp);
            }
            vo.setIndex(i+1);
            csvList.add(vo.getIndex()+","
                    +vo.getStudentNo()+","
                    +vo.getStudentName()+","
                    +vo.getTeacherName()+","
                    +vo.getPhone()+","
                    +("0".equals(vo.getType())?"外出":"返校")+","
                    +vo.getTimeLeave()+","
                    +vo.getTimeBack()+","
                    +map.get(vo.getState())+","
                    +map2.get(vo.getAuthorState()));
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fName, response);
            ExportUtil.doExport(csvList, os);
        } catch (Exception e) {
            log.error("生成csv文件失败:{}", ErrorUtil.err(e));
        }
    }
}
