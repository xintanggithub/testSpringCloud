package com.tson.yd.service.email.impl

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.LogCode
import com.tson.yd.service.email.EmailService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.util.StringUtils
import javax.mail.MessagingException


@Service
class EmailServiceImpl : EmailService {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var mailSender: JavaMailSender

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

    override fun sendHtmlMail(to: String, subject: String, content: String): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(to) || StringUtils.isEmpty(subject) || StringUtils.isEmpty(content)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        //获取MimeMessage对象
        val message = mailSender.createMimeMessage()
        val messageHelper: MimeMessageHelper
        try {
            messageHelper = MimeMessageHelper(message, true)
            //邮件发送人
            messageHelper.setFrom(from)
            //邮件接收人
            messageHelper.setTo(subject)
            //邮件主题
            message.subject = subject
            //邮件内容，html格式
            messageHelper.setText(content, true)
            //发送
            mailSender.send(message)
            //日志信息
            logger.info("邮件已经发送。")
            return response.also {
                it.setStatus(LogCode.RC_SUCCESS)
            }
        } catch (e: MessagingException) {
            logger.error("发送邮件时发生异常！", e)
            return response.also {
                it.setStatus(LogCode.RC_SEND_EMAIL_ERROR)
                it.data = e.message
            }
        }
    }
}
