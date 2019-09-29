package com.tson.yd.service.login.impl

import com.alibaba.fastjson.JSON
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.LogCode
import com.tson.yd.dao.LoginDao
import com.tson.yd.model.login.LoginEntity
import com.tson.yd.model.login.LoginUserIdEntity
import com.tson.yd.model.request.InsertUserRequest
import com.tson.yd.service.login.LoginService
import com.tson.yd.service.user.UserService
import com.tson.yd.utils.CharUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = "LoginService")
class LoginServiceImpl : LoginService {

    companion object {

        private val LOGGER = LoggerFactory.getLogger(LoginServiceImpl::class.java)
    }

    @Autowired
    private lateinit var userDao: LoginDao
    @Autowired
    private lateinit var userService: UserService

    override fun queryLogin(userCode: String): BaseResponse<LoginEntity> {
        LOGGER.debug("queryLogin ---> userCode = $userCode")
        return BaseResponse<LoginEntity>().also { root ->
            when (userCode.isEmpty()) {
                true -> root.setStatus(LogCode.RC_PARAMETER_ERROR)
                false -> {
                    userDao.queryLogin(userCode).also { cd ->
                        when (null == cd) {
                            true -> root.setStatus(LogCode.RC_USER_NONE)
                            else -> root.run {
                                data = cd
                                setStatus(LogCode.RC_SUCCESS)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun insertLogin(loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
        LOGGER.debug("insertLogin ---> request = ${JSON.toJSONString(loginEntity)}")
        val response = BaseResponse<LoginUserIdEntity>()
        //需要做参数校验
        if (loginEntity.userCode.isEmpty() || loginEntity.password.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
        //先判断是否有相关账号
        val queryLogin = queryLogin(loginEntity.userCode)
        //如果有查询到，返回：用户已存在
        if (queryLogin.resultCode == LogCode.RC_SUCCESS.code) {
            LOGGER.debug("insertLogin ---> User already exists => ${queryLogin.data}")
            return response.also {
                it.setStatus(LogCode.RC_USER_NOT_NONE)
            }
        }
        //没有查询到，就生成user信息和login信息
        val insertUser = userService.insertUser(InsertUserRequest().also {
            it.userName = loginEntity.userCode
        })
        LOGGER.debug("insertLogin ---> insertUser end => $insertUser")
        //如果插入用户信息未成功，提示失败
        if (insertUser.resultCode != LogCode.RC_SUCCESS.code) {
            LOGGER.debug("insertLogin ---> insertUser error => $insertUser")
            return response.also {
                it.resultCode = insertUser.resultCode
                it.resultMessage = insertUser.resultMessage
            }
        }
        //userId赋值
        loginEntity.userId = insertUser.data
        loginEntity.password = CharUtils.getPassword(loginEntity.password)
        LOGGER.debug("insertLogin ---> insertLogin  userCode = ${loginEntity.userCode}  password = ${loginEntity.password}")
        //插入user信息成功，开始插入login信息
        userDao.insertLogin(loginEntity)
        LOGGER.debug("insertLogin ---> insertLogin end")
        response.data = LoginUserIdEntity().also {
            it.userId = insertUser.data
        }
        response.setStatus(LogCode.RC_SUCCESS)
        return response
    }

    override fun login(loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
        LOGGER.debug("login ---> request = ${JSON.toJSONString(loginEntity)}")
        val response = BaseResponse<LoginUserIdEntity>()
        if (loginEntity.password.isEmpty() || loginEntity.userCode.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
        //查询用户信息，确认用户是否存在
        val queryLogin = queryLogin(loginEntity.userCode)
        if (queryLogin.resultCode == LogCode.RC_SUCCESS.code) {
            if (CharUtils.getPassword(loginEntity.password) == queryLogin.data.password) {
                response.data = LoginUserIdEntity().also {
                    it.userId = queryLogin.data.userId
                }
                response.setStatus(LogCode.RC_SUCCESS)
            } else {
                response.setStatus(LogCode.RC_USER_PASSWORD_ERROR)
            }
        } else {
            response.run {
                resultCode = queryLogin.resultCode
                resultMessage = queryLogin.resultMessage
            }
        }
        return response
    }

}
