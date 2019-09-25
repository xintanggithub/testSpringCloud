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
//todo 查询(需要整理 注册、登陆、注销逻辑)
}
