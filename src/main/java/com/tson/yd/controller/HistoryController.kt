package com.tson.yd.controller

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("HistoryController")
@RequestMapping("/v1/history")
@Api(value = "HistoryController 历史", description = "History", tags = ["History"])
class HistoryController {

}