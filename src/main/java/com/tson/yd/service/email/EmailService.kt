package com.tson.yd.service.email

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.email.EmailEntity
import com.tson.yd.model.email.InsertEmailRequest
import com.tson.yd.model.login.LoginEntity
import com.tson.yd.model.login.LoginUserIdEntity
import com.tson.yd.model.login.RegisterEntity
import org.springframework.web.bind.annotation.RequestBody

interface EmailService {

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    fun sendContentMail(to: String, subject: String, content: String): BaseResponse<String>

    /**
     * 插入邮箱验证数据
     *
     * @param request 验证数据
     */
    fun insertData(request: InsertEmailRequest): BaseResponse<Any>

    /**
     * 获取验证码数据
     *
     * @param userCode 账号
     * @return 返回验证码数据
     */
    fun queryEmailData(userCode: String): BaseResponse<EmailEntity>

    /**
     * 更新验证码数据
     * @param request 验证码
     */
    fun updateEmailData(request: InsertEmailRequest): BaseResponse<Any>

    /**
     * 发送验证码
     */
    fun sendVerificationCode(userCode: String): BaseResponse<String>

    /**
     * 注册账号
     */
    fun register(request: RegisterEntity): BaseResponse<String>

    fun queryLogin(userCode: String): BaseResponse<LoginEntity>
    fun login(loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity>

}
