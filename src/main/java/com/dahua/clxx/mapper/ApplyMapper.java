package com.dahua.clxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.pojo.ClxxApply;
import com.dahua.clxx.pojo.ApplyVo;
import com.dahua.clxx.pojo.ClxxApplyDto;
import org.apache.ibatis.annotations.Param;

public interface ApplyMapper extends BaseMapper<ClxxApply> {

    IPage<ApplyVo> queryApplyPage(Page<ClxxApplyDto> page, @Param("clxxApply") ClxxApplyDto apply);

}
