package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.game.GameEntity
import com.tson.yd.model.game.request.GameRequest
import com.tson.yd.service.game.GameService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("GameController")
@RequestMapping("/v1/game")
@Api(value = "GameController 配置", description = "game", tags = ["game"])
class GameController {

    @Autowired
    lateinit var gameService: GameService

    @RequestMapping(value = ["/insertGame"], method = [RequestMethod.POST])
    @ApiOperation(value = "新增game", notes = "v1.0.0")
    fun insertGame(@RequestBody request: GameRequest): BaseResponse<String> {
        return gameService.insertGame(request)
    }

    @RequestMapping(value = ["/deleteGame"], method = [RequestMethod.GET])
    @ApiOperation(value = "删除game", notes = "v1.0.0")
    fun deleteGame(@ApiParam(required = true, name = "id", value = "game id")
                   @RequestParam(value = "id", required = true) id: Int): BaseResponse<String> {
        return gameService.deleteGame(id)
    }

    @RequestMapping(value = ["/queryList"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询game", notes = "v1.0.0")
    fun queryList(@ApiParam(required = false, name = "keyword", value = "关键词")
                  @RequestParam(value = "keyword", required = false) keyword: String?,
                  @ApiParam(required = true, name = "page", value = "页码")
                  @RequestParam(value = "page", required = true) page: Int,
                  @ApiParam(required = true, name = "pageSize", value = "页码")
                  @RequestParam(value = "pageSize", required = true) pageSize: Int): BaseResponse<ListBaseData<GameEntity>> {
        return gameService.queryList(keyword, page, pageSize)
    }

}
