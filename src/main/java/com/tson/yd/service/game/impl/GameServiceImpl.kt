package com.tson.yd.service.game.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.GameDao
import com.tson.yd.model.game.GameEntity
import com.tson.yd.model.game.GameResponseEntity
import com.tson.yd.model.game.request.GameRequest
import com.tson.yd.service.collection.CollectionService
import com.tson.yd.service.game.GameService
import org.apache.commons.beanutils.BeanUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = "GameService")
class GameServiceImpl : GameService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(GameServiceImpl::class.java)
    }

    @Autowired
    private lateinit var gameDao: GameDao
    @Autowired
    lateinit var collectionService: CollectionService

    override fun insertGame(request: GameRequest): BaseResponse<String> {
        LOGGER.info("insert -- > ", request)
        val response = BaseResponse<String>()
        gameDao.insertGame(request)
        return response.also {
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun deleteGame(id: Int): BaseResponse<String> {
        LOGGER.info("deleteGame -- > ", id)
        val response = BaseResponse<String>()
        gameDao.deleteGame(id)
        return response.also {
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun queryList(keyword: String?, page: Int, pageSize: Int): BaseResponse<ListBaseData<GameEntity>> {
        LOGGER.info("queryList -- > page = $page    pageSize = $pageSize")
        val response = BaseResponse<ListBaseData<GameEntity>>()
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(gameDao.queryList(keyword))
        return response.also {
            it.data = ListBaseData<GameEntity>().also { l ->
                l.lists = pageInfo.list
                l.page = pageInfo.pageNum
                l.pageSize = pageInfo.pageSize
                l.totalCount = pageInfo.total.toInt()
            }
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun queryListToCollection(keyword: String?, userId: String?, page: Int, pageSize: Int): BaseResponse<ListBaseData<GameResponseEntity>> {
        val response = BaseResponse<ListBaseData<GameResponseEntity>>()
        val result = queryList(keyword, page, pageSize)
        if (result.resultCode == LogCode.RC_SUCCESS.code) {
            val baseData = ListBaseData<GameResponseEntity>()
            val list = mutableListOf<GameResponseEntity>()
            result.data.lists.forEach { item ->
                val itemData = GameResponseEntity()
                BeanUtils.copyProperties(itemData, item)
                list.add(itemData)
            }
            baseData.also {
                it.totalCount = result.data.totalCount
                it.page = result.data.page
                it.pageSize = result.data.pageSize
                it.lists = list
            }
            if (null != userId && userId.isNotEmpty()) {
                // 查询是否已收藏
                baseData.lists.forEach { data ->
                    val res = collectionService.queryCollection(userId, data.id, 1)
                    data.collection = when (res.resultCode) {
                        LogCode.RC_SUCCESS.code -> 1
                        else -> 0
                    }
                }
            }
            return response.also {
                it.setStatus(LogCode.RC_SUCCESS)
                it.data = baseData
            }
        } else {
            return response.also {
                it.resultMessage = result.resultMessage
                it.resultCode = result.resultCode
            }
        }
    }

}