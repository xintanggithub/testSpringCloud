package com.tson.yd.service.game.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.GameDao
import com.tson.yd.model.game.GameEntity
import com.tson.yd.model.game.request.GameRequest
import com.tson.yd.service.game.GameService
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

    override fun insertGame(request: GameRequest): BaseResponse<String> {
        LOGGER.debug("insert -- > ", request)
        val response = BaseResponse<String>()
        gameDao.insertGame(request)
        return response.also {
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun deleteGame(id: Int): BaseResponse<String> {
        LOGGER.debug("deleteGame -- > ", id)
        val response = BaseResponse<String>()
        gameDao.deleteGame(id)
        return response.also {
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun queryList(page: Int, pageSize: Int): BaseResponse<ListBaseData<GameEntity>> {
        LOGGER.debug("queryList -- > page = $page    pageSize = $pageSize")
        val response = BaseResponse<ListBaseData<GameEntity>>()
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(gameDao.queryList())
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


}