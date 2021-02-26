package com.dahua.clxx.service;

import com.dahua.clxx.pojo.PersonFaceImgDto;

public interface CardPersonService {
    /**
     * 定期清理登录失效的用户
     */
    void removeUser();

    /**
     * 修改人脸照片
     */
    boolean updFaceImg(PersonFaceImgDto personFaceDto);

    /**
     * 获取dssToken
     */
    void refreshDssToken();

    /**
     * 添加权限
     */
    void addPrivilige();

    /**
     * 删除权限
     */
    void removePrivilige();

}
