package com.tson.yd.dao;

import com.tson.yd.model.email.EmailEntity;
import com.tson.yd.model.email.InsertEmailRequest;

public interface EmailDao {

    /**
     * 插入邮箱验证数据
     *
     * @param request 验证数据
     */
    void insertData(InsertEmailRequest request);

    /**
     * 获取验证码数据
     *
     * @param userCode 账号
     * @return 返回验证码数据
     */
    EmailEntity queryEmailData(String userCode);

    /**
     * 更新验证码数据
     * @param request 验证码
     */
    void updateEmailData(InsertEmailRequest request);

}
