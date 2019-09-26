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
     * 更新密码
     *
     * @param password 密码
     */
    void updatePassword(String password);

    /**
     * 更新用户注销状态
     *
     * @param register 注册 状态 1正常  0注销
     */
    void updateRegister(String register);

    /**
     * 更新账号数据
     * @param password 密码
     * @param register 注册状态 1正常 0注销
     */
    void updateLogin(String password, String register);

    /**
     * 新增
     * @param loginEntity 账号数据
     */
    void insertLogin(LoginEntity loginEntity);

}
