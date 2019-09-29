package com.tson.yd.controller

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("ConfigController")
@RequestMapping("/v1/config")
@Api(value = "ConfigController 配置", description = "config", tags = ["config"])
class ConfigController{

}