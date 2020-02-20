package com.tson.yd.service.collection.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.CollectionDao
import com.tson.yd.model.collection.CollectionEntity
import com.tson.yd.model.collection.CollectionResponseEntity
import com.tson.yd.service.collection.CollectionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CollectionServiceImpl : CollectionService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CollectionServiceImpl::class.java)
    }

    @Autowired
    private lateinit var collectionDao: CollectionDao

    override fun insertCollection(request: CollectionEntity): BaseResponse<Any> {
        val response = BaseResponse<Any>()
        if (request.userId.isEmpty() || request.collectionType == -1 || request.gameId == -1) {
            return response.also { it.setStatus(LogCode.RC_PARAMETER_ERROR) }
        }
        collectionDao.insertCollection(request)
        return response.also { it.setStatus(LogCode.RC_SUCCESS) }
    }

    override fun deleteCollection(userId: String, gameId: Int, collectionType: Int): BaseResponse<Any> {
        val response = BaseResponse<Any>()
        if (userId.isEmpty() || gameId == -1) {
            return response.also { it.setStatus(LogCode.RC_PARAMETER_ERROR) }
        }
        collectionDao.deleteCollection(userId, gameId, collectionType)
        return response.also { it.setStatus(LogCode.RC_SUCCESS) }
    }

    override fun queryCollectionByUser(userId: String, collectionType: Int, page: Int, pageSize: Int): BaseResponse<ListBaseData<CollectionResponseEntity>> {
        LOGGER.info("queryCollectionByUser -- > page = $page    pageSize = $pageSize")
        val response = BaseResponse<ListBaseData<CollectionResponseEntity>>()
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(collectionDao.queryCollectionByUser(userId, collectionType))
        return response.also {
            it.data = ListBaseData<CollectionResponseEntity>().also { l ->
                l.lists = pageInfo.list
                l.page = pageInfo.pageNum
                l.pageSize = pageInfo.pageSize
                l.totalCount = pageInfo.total.toInt()
            }
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun queryCollection(userId: String, gameId: Int, collectionType: Int): BaseResponse<CollectionResponseEntity> {
        val response = BaseResponse<CollectionResponseEntity>()
        if (userId.isEmpty() || gameId == -1) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
        val collection = collectionDao.queryCollection(userId, gameId, collectionType)
        if (null !== collection) {
            response.run {
                data = collection
                setStatus(LogCode.RC_SUCCESS)
            }
        } else {
            response.run { setStatus(LogCode.RC_RESULT_EMPTY) }
        }
        return response
    }

}