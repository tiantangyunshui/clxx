package com.dahua.clxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.exception.BusinessRuntimeException;
import com.dahua.clxx.mapper.ApplyMapper;
import com.dahua.clxx.pojo.*;
import com.dahua.clxx.service.ApplyService;
import com.dahua.clxx.service.CardPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Slf4j
@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private CardPersonService cardPersonService;

    @Override
    public IPage<ApplyVo> queryApplyPage(ClxxApplyDto apply, int page, int size) {
        log.info("page:{}\nsize:{}\napply:{}",page,size,apply);
        if("3".equals(apply.getState())){
            apply.setState(null);
            apply.setStateNull("0");
        }
        return applyMapper.queryApplyPage(new Page<>(page, size), apply);
    }

    @Override
    public void addApply(ClxxApplyDto apply) {
        //TODO 人脸更新至8900
        if(apply.getBase64file()!=null && !"".equals(apply.getBase64file())){
            PersonFaceImgDto img = new PersonFaceImgDto();
            img.setPersonCode(apply.getStudentId()+"");
            img.setBase64file(apply.getBase64file());
            cardPersonService.updFaceImg(img);
        }
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
    public ApplyVo queryApplyPage(Long id) {
        ClxxApplyDto apply = new ClxxApplyDto();
        apply.setId(id);
        IPage<ApplyVo> page = applyMapper.queryApplyPage(new Page<>(1, 1), apply);
        if(page.getRecords().size()==0){
            throw new BusinessRuntimeException(500, "数据不存在");
        }
        return page.getRecords().get(0);
    }
}
