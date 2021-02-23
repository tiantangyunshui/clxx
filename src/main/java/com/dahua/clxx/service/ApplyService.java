package com.dahua.clxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dahua.clxx.pojo.Apply;
import com.dahua.clxx.pojo.ApplyStateDto;
import com.dahua.clxx.pojo.ApplyVo;

public interface ApplyService {

    /**
     * 申请列表查询
     */
    IPage<ApplyVo> queryApplyPage(Apply apply, int page, int size);

    /**
     * 添加申请
     */
    void addApply(Apply apply);

    /**
     * 申请状态修改
     */
    void updApply(ApplyStateDto apply);
}
