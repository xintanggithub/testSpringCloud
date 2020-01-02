package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.history.BaseHistory
import com.tson.yd.model.history.HistoryResponse
import com.tson.yd.service.history.HistoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("HistoryController")
@RequestMapping("/v1/history")
@Api(value = "HistoryController 历史", description = "History", tags = ["History"])
class HistoryController {

    @Autowired
    lateinit var historyService: HistoryService

    @RequestMapping(value = ["/insertHistory"], method = [RequestMethod.POST])
    @ApiOperation(value = "新增历史记录", notes = "v1.0.0")
    fun insertHistory(@RequestBody baseHistory: BaseHistory): BaseResponse<String> = historyService.insertHistory(baseHistory)

    @RequestMapping(value = ["/queryHistoryByUser"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询用户历史记录", notes = "v1.0.0")
    fun queryBooksByUser(@ApiParam(required = true, name = "userId", value = "用户ID")
                         @RequestParam(value = "userId", required = true) userId: String,
                         @ApiParam(required = true, name = "page", value = "页码")
                         @RequestParam(value = "page", required = true) page: Int,
                         @ApiParam(required = true, name = "pageSize", value = "页码")
                         @RequestParam(value = "pageSize", required = true) pageSize: Int): BaseResponse<ListBaseData<HistoryResponse>> = historyService.queryHistoryByUser(userId, page, pageSize)

    @RequestMapping(value = ["/queryHistoryByType"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询类型历史记录", notes = "v1.0.0")
    fun queryHistoryByType(@ApiParam(required = true, name = "type", value = "类型")
                           @RequestParam(value = "type", required = true) type: String,
                           @ApiParam(required = true, name = "page", value = "页码")
                           @RequestParam(value = "page", required = true) page: Int,
                           @ApiParam(required = true, name = "pageSize", value = "页码")
                           @RequestParam(value = "pageSize", required = true) pageSize: Int): BaseResponse<ListBaseData<HistoryResponse>> = historyService.queryHistoryByType(type, page, pageSize)

    @RequestMapping(value = ["/updateDel"], method = [RequestMethod.POST])
    @ApiOperation(value = "修改历史记录删除状态", notes = "v1.0.0")
    fun updateDel(@RequestBody baseHistory: BaseHistory): BaseResponse<String> = historyService.updateDel(baseHistory)

}