package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.login.LoginEntity
import com.tson.yd.model.login.LoginUserIdEntity
import com.tson.yd.model.login.RegisterEntity
import com.tson.yd.service.email.EmailService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("LoginController")
@RequestMapping("/v1/login")
@Api(value = "LoginController 账号", description = "login", tags = ["login"])
class LoginController {

    @Autowired
    private lateinit var emailService: EmailService

    @RequestMapping(value = ["/queryLoginByUserCode"], method = [RequestMethod.GET])
//    @ApiOperation(value = "根据账号查询账号信息", notes = "v1.0.0")
    fun queryLoginByUserCode(@ApiParam(required = true, name = "userCode", value = "账号")
                             @RequestParam(value = "userCode", required = true)
                             userCode: String): BaseResponse<LoginEntity> {
        return emailService.queryLogin(userCode)
    }

//    @RequestMapping(value = ["/registerLogin"], method = [RequestMethod.POST])
//    @ApiOperation(value = "注册账号", notes = "v1.0.0")
//    fun registerLogin(@RequestBody loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
//        return loginService.insertLogin(loginEntity)
//    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    @ApiOperation(value = "账号登录", notes = "v1.0.0")
    fun login(@RequestBody loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
        return emailService.login(loginEntity)
    }

    @RequestMapping(value = ["/sendVerificationCode"], method = [RequestMethod.GET])
    @ApiOperation(value = "发送验证码", notes = "v1.0.0")
    fun sendVerificationCode(@ApiParam(required = true, name = "userCode", value = "账号")
                             @RequestParam(value = "userCode", required = true)
                             userCode: String): BaseResponse<String> {
        return emailService.sendVerificationCode(userCode)
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    @ApiOperation(value = "注册账号", notes = "v1.0.0")
    fun register(@RequestBody request: RegisterEntity): BaseResponse<String> {
        return emailService.register(request)
    }

    @RequestMapping(value = ["/updateLogin"], method = [RequestMethod.POST])
    @ApiOperation(value = "修改密码", notes = "v1.0.0")
    fun updateLogin(@RequestBody loginEntity: LoginEntity): BaseResponse<Any> {
        return emailService.updateLogin(loginEntity)
    }

    @RequestMapping(value = ["/updateRegister"], method = [RequestMethod.POST])
    @ApiOperation(value = "账号冻结", notes = "v1.0.0")
    fun updateRegister(@RequestBody loginEntity: LoginEntity): BaseResponse<Any> {
        return emailService.updateRegister(loginEntity)
    }

//    @RequestMapping(value = ["/sendEmail"], method = [RequestMethod.POST])
//    @ApiOperation(value = "发送邮件", notes = "v1.0.0")
//    fun sendEmail(@RequestBody request: EmailRequest): BaseResponse<String> {
//        return emailService.sendContentMail(request.to, request.subject, "验证码：${request.content}")
//    }

    @RequestMapping(value = ["/checkVerificationCode"], method = [RequestMethod.GET])
    @ApiOperation(value = "校验验证码", notes = "v1.0.0")
    fun checkVerificationCode(@ApiParam(required = true, name = "userCode", value = "账号")
                              @RequestParam(value = "userCode", required = true)
                              userCode: String, @ApiParam(required = true, name = "verificationCode", value = "验证码")
                              @RequestParam(value = "verificationCode", required = true)
                              verificationCode: String): BaseResponse<Any> {
        return emailService.checkEmailCode(userCode, verificationCode)
    }

    @RequestMapping(value = ["/checkRegister"], method = [RequestMethod.GET])
    @ApiOperation(value = "校验是否已注册", notes = "v1.0.0")
    fun checkRegister(@ApiParam(required = true, name = "userCode", value = "账号")
                      @RequestParam(value = "userCode", required = true)
                      userCode: String): BaseResponse<Boolean> {
        return emailService.checkRegister(userCode)
    }

}
