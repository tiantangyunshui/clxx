package com.dahua.clxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.mapper.ApplyMapper;
import com.dahua.clxx.pojo.Apply;
import com.dahua.clxx.pojo.ApplyStateDto;
import com.dahua.clxx.pojo.ApplyVo;
import com.dahua.clxx.pojo.PersonFaceImgDto;
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
    public IPage<ApplyVo> queryApplyPage(Apply apply, int page, int size) {
        log.info("page:{}\nsize:{}\napply:{}",page,size,apply);
        return applyMapper.queryApplyPage(new Page<>(page, size), apply);
    }

    @Override
    public void addApply(Apply apply) {
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
        Apply apply = new Apply();
        apply.setId(applyDto.getId());
        apply.setState(applyDto.getState());
        applyMapper.updateById(apply);
    }
}
