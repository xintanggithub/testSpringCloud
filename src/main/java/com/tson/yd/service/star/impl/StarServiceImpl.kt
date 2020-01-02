package com.tson.yd.service.star.impl

import com.alibaba.druid.util.StringUtils
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.StarDao
import com.tson.yd.model.star.BaseStar
import com.tson.yd.model.star.StarResponse
import com.tson.yd.service.star.StarService
import com.tson.yd.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = "StarService")
class StarServiceImpl : StarService {

    @Autowired
    private lateinit var starDao: StarDao
    @Autowired
    private lateinit var userService: UserService

    override fun queryRetryStar(baseStar: BaseStar): BaseResponse<Int> {
        val result = starDao.queryRetryStar(baseStar)
        return BaseResponse<Int>().also {
            it.data = result
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun insertStar(baseStar: BaseStar): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(baseStar.starUserId) || StringUtils.isEmpty(baseStar.userId)
                || StringUtils.isEmpty(baseStar.starUserName)
                || (baseStar.starStatus != 1 && baseStar.starStatus != 0)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val result = queryRetryStar(baseStar)
        if (result.resultCode == LogCode.RC_SUCCESS.code) {
            if (result.data >= 1) {
                response.setStatus(LogCode.RC_RETRY_START)
            } else {
                val responseByUserId = userService.queryUserById(baseStar.userId)
                if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
                    val responseByStartUserId = userService.queryUserById(baseStar.starUserId)
                    if (responseByStartUserId.resultCode == LogCode.RC_SUCCESS.code) {
                        starDao.insertStar(baseStar)
                        response.setStatus(LogCode.RC_SUCCESS)
                    } else {
                        response.resultCode = responseByStartUserId.resultCode
                        response.resultMessage = responseByStartUserId.resultMessage
                    }
                } else {
                    response.resultCode = responseByUserId.resultCode
                    response.resultMessage = responseByUserId.resultMessage
                }
            }
        } else {
            response.resultCode = result.resultCode
            response.resultMessage = result.resultMessage
        }
        return response
    }

    override fun updateStatus(baseStar: BaseStar): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(baseStar.starUserId) || StringUtils.isEmpty(baseStar.userId)
                || (baseStar.starStatus != 1 && baseStar.starStatus != 0)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        starDao.updateStatus(baseStar)
        response.setStatus(LogCode.RC_SUCCESS)
        return response
    }

    override fun queryBStar(userId: String): BaseResponse<Int> {
        val response = BaseResponse<Int>()
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(userId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
            val result = starDao.queryBStar(userId)
            response.setStatus(LogCode.RC_SUCCESS)
            response.data = result
        } else {
            response.resultCode = responseByUserId.resultCode
            response.resultMessage = responseByUserId.resultMessage
        }
        return response
    }

    override fun queryWStar(starUserId: String): BaseResponse<Int> {
        val response = BaseResponse<Int>()
        if (StringUtils.isEmpty(starUserId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(starUserId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
            val result = starDao.queryWStar(starUserId)
            response.setStatus(LogCode.RC_SUCCESS)
            response.data = result
        } else {
            response.resultCode = responseByUserId.resultCode
            response.resultMessage = responseByUserId.resultMessage
        }
        return response
    }

    override fun queryStarList(userId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<StarResponse>> {
        val response = BaseResponse<ListBaseData<StarResponse>>()
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(userId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
            PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
            val pageInfo = PageInfo(starDao.queryStarList(userId))
            if (pageInfo.list.isEmpty()) {
                response.resultCode = LogCode.RC_RESULT_EMPTY.code
                response.resultMessage = LogCode.RC_RESULT_EMPTY.message
                return response
            }
            response.also {
                it.data = ListBaseData<StarResponse>().also { l ->
                    l.lists = pageInfo.list
                    l.page = pageInfo.pageNum
                    l.pageSize = pageInfo.pageSize
                    l.totalCount = pageInfo.total.toInt()
                }
                it.setStatus(LogCode.RC_SUCCESS)
            }
        } else {
            response.resultCode = responseByUserId.resultCode
            response.resultMessage = responseByUserId.resultMessage
        }
        return response
    }

    override fun queryWStarList(starUserId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<StarResponse>> {
        val response = BaseResponse<ListBaseData<StarResponse>>()
        if (StringUtils.isEmpty(starUserId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(starUserId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
            PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
            val pageInfo = PageInfo(starDao.queryWStarList(starUserId))
            if (pageInfo.list.isEmpty()) {
                response.resultCode = LogCode.RC_RESULT_EMPTY.code
                response.resultMessage = LogCode.RC_RESULT_EMPTY.message
                return response
            }
            response.also {
                it.data = ListBaseData<StarResponse>().also { l ->
                    l.lists = pageInfo.list
                    l.page = pageInfo.pageNum
                    l.pageSize = pageInfo.pageSize
                    l.totalCount = pageInfo.total.toInt()
                }
                it.setStatus(LogCode.RC_SUCCESS)
            }
        } else {
            response.resultCode = responseByUserId.resultCode
            response.resultMessage = responseByUserId.resultMessage
        }
        return response
    }

}