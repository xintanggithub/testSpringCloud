package com.tson.yd.service.collection

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.collection.CollectionEntity
import com.tson.yd.model.collection.CollectionResponseEntity

interface CollectionService {

    /**
     * 新增收藏
     *
     * @param request 收藏请求实体
     */
    fun insertCollection(request: CollectionEntity): BaseResponse<Any>

    /**
     * 删除收藏
     *
     * @param userId 用户ID
     * @param gameId gameId
     */
    fun deleteCollection(userId: String, gameId: Int, collectionType: Int): BaseResponse<Any>

    /**
     * 查询用户的所有收藏
     *
     * @param userId 用户ID
     * @return 收藏记录
     */
    fun queryCollectionByUser(userId: String, collectionType: Int, page: Int, pageSize: Int): BaseResponse<ListBaseData<CollectionResponseEntity>>

    /**
     * 查询某一条收藏记录
     *
     * @param userId 用户ID
     * @param gameId gameId
     * @return 收藏信息
     */
    fun queryCollection(userId: String, gameId: Int, collectionType: Int): BaseResponse<CollectionResponseEntity>

}
