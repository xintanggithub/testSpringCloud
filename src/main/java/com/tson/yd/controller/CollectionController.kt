package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.collection.CollectionEntity
import com.tson.yd.model.collection.CollectionResponseEntity
import com.tson.yd.service.collection.CollectionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("CollectionController")
@RequestMapping("/v1/collection")
@Api(value = "collection 收藏", description = "collection", tags = ["collection"])
class CollectionController {

    @Autowired
    lateinit var collectionService: CollectionService

    @RequestMapping(value = ["/insertCollection"], method = [RequestMethod.POST])
    @ApiOperation(value = "新增收藏", notes = "v1.0.0")
    fun insertCollection(@RequestBody insertBootEntity: CollectionEntity): BaseResponse<Any> {
        return collectionService.insertCollection(insertBootEntity)
    }

    @RequestMapping(value = ["/deleteCollection"], method = [RequestMethod.GET])
    @ApiOperation(value = "删除收藏", notes = "v1.0.0")
    fun deleteCollection(@ApiParam(required = true, name = "userId", value = "用户ID")
                         @RequestParam(value = "userId", required = true) userId: String,
                         @ApiParam(required = true, name = "gameId", value = "gameId")
                         @RequestParam(value = "gameId", required = true) gameId: Int,
                         @ApiParam(required = true, name = "collectionType", value = "收藏类型 1.game")
                         @RequestParam(value = "collectionType", required = true) collectionType: Int): BaseResponse<Any> {
        return collectionService.deleteCollection(userId, gameId, collectionType)
    }

    @RequestMapping(value = ["/queryCollectionByUser"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询用户下所有收藏", notes = "v1.0.0")
    fun queryCollectionByUser(@ApiParam(required = true, name = "userId", value = "userId")
                              @RequestParam(value = "userId", required = true) userId: String,
                              @ApiParam(required = true, name = "collectionType", value = "收藏类型 1.game")
                              @RequestParam(value = "collectionType", required = true)
                              collectionType: Int,
                              @ApiParam(required = true, name = "page", value = "页码")
                              @RequestParam(value = "page", required = true) page: Int,
                              @ApiParam(required = true, name = "pageSize", value = "页码")
                              @RequestParam(value = "pageSize", required = true) pageSize: Int): BaseResponse<ListBaseData<CollectionResponseEntity>> {
        return collectionService.queryCollectionByUser(userId, collectionType, page, pageSize)
    }

}