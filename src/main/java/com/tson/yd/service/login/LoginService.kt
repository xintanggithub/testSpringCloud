package com.tson.yd.service.login

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.login.LoginEntity
import com.tson.yd.model.login.LoginUserIdEntity

interface LoginService {

    /**
     * 根据账号查询账号信息
     *
     * @param userCode 账号
     * @return 账号信息
     */
    fun queryLogin(userCode: String): BaseResponse<LoginEntity>

    /**
     * 注册账号
     *
     * @param loginEntity 账号信息
     * @return 返回userId
     */
    fun insertLogin(loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity>

    /**
     * 账号登录
     * @param loginEntity 账号信息
     * @return 返回userId
     */
    fun login(loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity>

    /**
     * 更新登录的账号数据
     */
    fun updateLogin(loginEntity: LoginEntity): BaseResponse<Any>

    fun updateRegister(loginEntity: LoginEntity): BaseResponse<Any>

}
