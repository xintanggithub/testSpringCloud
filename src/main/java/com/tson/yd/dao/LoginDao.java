package com.tson.yd.dao;

import com.tson.yd.model.login.LoginEntity;

public interface LoginDao {

    /**
     * 根据账号查询账号信息
     *
     * @param userCode 账号
     * @return 账号信息
     */
    LoginEntity queryLogin(String userCode);

    /**
     * 新增
     * @param loginEntity 账号数据
     */
    void insertLogin(LoginEntity loginEntity);

    /**
     * 更新账号数据
     */
    void updateLogin(LoginEntity loginEntity);

}
