package com.dahua.clxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dahua.clxx.pojo.ClxxApply;
import com.dahua.clxx.pojo.ApplyStateDto;
import com.dahua.clxx.pojo.ApplyVo;
import com.dahua.clxx.pojo.ClxxApplyDto;

import javax.servlet.http.HttpServletResponse;

public interface ApplyService {

    /**
     * 申请列表查询
     */
    IPage<ApplyVo> queryApplyPage(ClxxApplyDto apply, int page, int size);

    /**
     * 添加申请
     */
    void addApply(ClxxApplyDto apply);

    /**
     * 申请状态修改
     */
    void updApply(ApplyStateDto apply);

    /**
     * 申请详情
     */
    ApplyVo queryApplyPage(String id);

    /**
     * 导出
     */
    void export(String ids, HttpServletResponse response);
}
