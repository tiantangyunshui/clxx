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
}
