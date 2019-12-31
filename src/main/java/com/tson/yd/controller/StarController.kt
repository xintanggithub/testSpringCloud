package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.star.BaseStar
import com.tson.yd.service.star.StarService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("StarController")
@RequestMapping("/v1/star")
@Api(value = "StarController 评星", description = "star", tags = ["star"])
class StarController {

    @Autowired
    lateinit var starService: StarService

    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    @ApiOperation(value = "新增", notes = "v1.0.0")
    fun insertStar(@RequestBody baseStar: BaseStar): BaseResponse<String> = starService.insertStar(baseStar)

    @RequestMapping(value = ["/updateStatus"], method = [RequestMethod.POST])
    @ApiOperation(value = "更新", notes = "v1.0.0")
    fun updateStatus(@RequestBody baseStar: BaseStar): BaseResponse<String> = starService.updateStatus(baseStar)

    @RequestMapping(value = ["/queryBStar"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询被点赞数量", notes = "v1.0.0")
    fun queryBStar(@ApiParam(required = true, name = "userId", value = "用户ID")
                   @RequestParam(value = "userId", required = true) userId: String) = starService.queryBStar(userId)

    @RequestMapping(value = ["/queryWStar"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询点赞数量", notes = "v1.0.0")
    fun queryWStar(@ApiParam(required = true, name = "starUserId", value = "点赞用户的ID")
                   @RequestParam(value = "starUserId", required = true) starUserId: String) = starService.queryWStar(starUserId)

    @RequestMapping(value = ["/queryStarList"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询被点赞列表", notes = "v1.0.0")
    fun queryStarList(@ApiParam(required = true, name = "userId", value = "用户ID")
                      @RequestParam(value = "userId", required = true) userId: String,
                      @ApiParam(required = true, name = "page", value = "页码")
                      @RequestParam(value = "page", required = true) page: Int,
                      @ApiParam(required = true, name = "pageSize", value = "页码")
                      @RequestParam(value = "pageSize", required = true) pageSize: Int) =
            starService.queryStarList(userId, page, pageSize)

    @RequestMapping(value = ["/queryWStarList"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询点赞列表", notes = "v1.0.0")
    fun queryWStarList(@ApiParam(required = true, name = "userId", value = "用户ID")
                       @RequestParam(value = "userId", required = true) userId: String,
                       @ApiParam(required = true, name = "page", value = "页码")
                       @RequestParam(value = "page", required = true) page: Int,
                       @ApiParam(required = true, name = "pageSize", value = "页码")
                       @RequestParam(value = "pageSize", required = true) pageSize: Int) =
            starService.queryWStarList(userId, page, pageSize)

}