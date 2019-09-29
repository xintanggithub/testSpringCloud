package com.tson.yd.service.email.impl

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.LogCode
import com.tson.yd.dao.EmailDao
import com.tson.yd.model.email.EmailEntity
import com.tson.yd.model.email.InsertEmailRequest
import com.tson.yd.model.login.LoginEntity
import com.tson.yd.model.login.LoginUserIdEntity
import com.tson.yd.model.login.RegisterEntity
import com.tson.yd.service.email.EmailService
import com.tson.yd.service.login.LoginService
import com.tson.yd.utils.CharUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.thymeleaf.util.StringUtils


@Service
class EmailServiceImpl : EmailService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(EmailServiceImpl::class.java)
    }

    @Autowired
    private lateinit var loginService: LoginService

    @Autowired
    private lateinit var mailSender: JavaMailSender

    @Autowired
    private lateinit var emailDao: EmailDao

    /**
     * 配置文件中我的qq邮箱
     */
    @Value("\${spring.mail.from}")
    private lateinit var from: String

    override fun sendContentMail(to: String, subject: String, content: String): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(to) || StringUtils.isEmpty(subject) || StringUtils.isEmpty(content)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val message = SimpleMailMessage()
        //邮件发送人
        message.from = from
        //邮件接收人
        message.setTo(to)
        //邮件主题
        message.subject = subject
        //邮件内容
        message.text = content
        //发送邮件
        mailSender.send(message)
        response.setStatus(LogCode.RC_SUCCESS)
        return response
    }

    override fun queryEmailData(userCode: String): BaseResponse<EmailEntity> {
        val response = BaseResponse<EmailEntity>()
        if (StringUtils.isEmpty(userCode)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val result = emailDao.queryEmailData(userCode)
        if (null == result) {
            response.setStatus(LogCode.RC_QUERY_EMAIL_ERROR)
            return response
        }
        response.data = result
        response.setStatus(LogCode.RC_SUCCESS)
        return response
    }

    override fun insertData(request: InsertEmailRequest): BaseResponse<Any> {
        val response = BaseResponse<Any>()
        if (StringUtils.isEmpty(request.userCode) || StringUtils.isEmpty(request.verificationCode)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val queryResult = queryEmailData(request.userCode)
        return if (queryResult.resultCode == LogCode.RC_QUERY_EMAIL_ERROR.code) {
            emailDao.insertData(request)
            response.setStatus(LogCode.RC_SUCCESS)
            response
        } else {
            updateEmailData(request)
        }
    }

    override fun updateEmailData(request: InsertEmailRequest): BaseResponse<Any> {
        val response = BaseResponse<Any>()
        if (StringUtils.isEmpty(request.userCode) || StringUtils.isEmpty(request.verificationCode)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val queryResult = queryEmailData(request.userCode)
        return if (queryResult.resultCode == LogCode.RC_QUERY_EMAIL_ERROR.code) {
            insertData(request)
        } else {
            emailDao.updateEmailData(request)
            response.also { it.setStatus(LogCode.RC_SUCCESS) }
        }
    }

    override fun sendVerificationCode(userCode: String): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(userCode)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val verificationCode = CharUtils.verificationCode()
        LOGGER.debug("sendVerificationCode  ----> userCode = $userCode       //// verificationCode = $verificationCode")
        val result = insertData(InsertEmailRequest().also {
            it.userCode = userCode
            it.verificationCode = verificationCode
        })
        if (result.resultCode == LogCode.RC_SUCCESS.code) {
            return sendContentMail(userCode, "验证码", "您的验证码为： $verificationCode")
        } else {
            return response.also {
                it.setStatus(LogCode.RC_VERIFICATION_CODE_SEND_ERROR)
            }
        }
    }

    override fun register(request: RegisterEntity): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(request.verificationCode) || StringUtils.isEmpty(request.userCode)
                || StringUtils.isEmpty(request.password)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val queryCode = queryEmailData(request.userCode)
        if (queryCode.resultCode == LogCode.RC_SUCCESS.code) {
            return if (queryCode.data.verificationCode == request.verificationCode) {
                val insertResult = loginService.insertLogin(request)
                if (insertResult.resultCode == LogCode.RC_SUCCESS.code) {
                    response.also {
                        it.setStatus(LogCode.RC_SUCCESS)
                    }
                } else {
                    response.also {
                        it.resultCode = insertResult.resultCode
                        it.resultMessage = insertResult.resultMessage
                    }
                }
            } else {
                response.also {
                    it.setStatus(LogCode.RC_VERIFICATION_CODE_ERROR)
                }
            }
        } else {
            return response.also {
                it.resultCode = queryCode.resultCode
                it.resultMessage = queryCode.resultMessage
            }
        }
    }

    override fun queryLogin(userCode: String): BaseResponse<LoginEntity> {
        return loginService.queryLogin(userCode)
    }

    override fun login(loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
        return loginService.login(loginEntity)
    }

    override fun updateLogin(loginEntity: LoginEntity): BaseResponse<Any> {
        return loginService.updateLogin(loginEntity)
    }

    override fun updateRegister(loginEntity: LoginEntity): BaseResponse<Any> {
        return loginService.updateRegister(loginEntity)
    }

}
