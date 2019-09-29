package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.login.LoginEntity
import com.tson.yd.model.login.LoginUserIdEntity
import com.tson.yd.service.login.LoginService
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
    private lateinit var loginService: LoginService

    @RequestMapping(value = ["/queryLoginByUserCode"], method = [RequestMethod.GET])
    @ApiOperation(value = "根据账号查询账号信息", notes = "v1.0.0")
    fun queryLoginByUserCode(@ApiParam(required = true, name = "userCode", value = "账号")
                             @RequestParam(value = "userCode", required = true)
                             userCode: String): BaseResponse<LoginEntity> {
        return loginService.queryLogin(userCode)
    }

    @RequestMapping(value = ["/registerLogin"], method = [RequestMethod.POST])
    @ApiOperation(value = "注册账号", notes = "v1.0.0")
    fun registerLogin(@RequestBody loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
        return loginService.insertLogin(loginEntity)
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    @ApiOperation(value = "账号登录", notes = "v1.0.0")
    fun login(@RequestBody loginEntity: LoginEntity): BaseResponse<LoginUserIdEntity> {
        return loginService.login(loginEntity)
    }

}
