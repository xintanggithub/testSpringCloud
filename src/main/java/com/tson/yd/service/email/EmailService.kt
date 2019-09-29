package com.tson.yd.service.email

import com.tson.yd.base.BaseResponse

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
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    fun sendHtmlMail(to: String, subject: String, content: String): BaseResponse<String>

}
