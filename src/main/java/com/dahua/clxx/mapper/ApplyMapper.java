package com.dahua.clxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dahua.clxx.pojo.Apply;
import com.dahua.clxx.pojo.ApplyVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ApplyMapper extends BaseMapper<Apply> {

    IPage<ApplyVo> queryApplyPage(Page<Apply> page, @Param("apply")Apply apply);

}
