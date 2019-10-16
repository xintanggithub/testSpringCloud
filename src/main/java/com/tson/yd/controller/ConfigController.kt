package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.main.MainConfigEntity
import com.tson.yd.service.main.MainService
import com.tson.yd.service.main.impl.MainServiceImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController("ConfigController")
@RequestMapping("/v1/config")
@Api(value = "ConfigController 配置", description = "config", tags = ["config"])
class ConfigController {

    @Autowired
    private lateinit var mainService: MainService

    @RequestMapping(value = ["/queryMainConfig"], method = [RequestMethod.GET])
    @ApiOperation(value = "获取首页配置信息", notes = "v1.0.0")
    fun queryMainConfig(): BaseResponse<MainConfigEntity> {
        return mainService.queryMainConfig()
    }

}