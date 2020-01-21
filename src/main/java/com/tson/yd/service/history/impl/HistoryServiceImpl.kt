package com.tson.yd.service.history.impl

import com.alibaba.druid.util.StringUtils
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.HistoryDao
import com.tson.yd.model.history.BaseHistory
import com.tson.yd.model.history.HistoryResponse
import com.tson.yd.service.history.HistoryService
import com.tson.yd.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = "HistoryService")
class HistoryServiceImpl : HistoryService {

    @Autowired
    private lateinit var historyDao: HistoryDao
    @Autowired
    private lateinit var userService: UserService

    override fun insertHistory(baseHistory: BaseHistory): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(baseHistory.userId) || StringUtils.isEmpty(baseHistory.type)
                || StringUtils.isEmpty(baseHistory.businessName)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(baseHistory.userId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {

            val resultCheck = historyDao.queryHistoryByAll(BaseHistory().also {
                it.businessId = baseHistory.businessId
                it.businessName = baseHistory.businessName
                it.type = baseHistory.type
                it.userId = baseHistory.userId
            })
            if (null != resultCheck && resultCheck.size > 0) {
                historyDao.updateAll(baseHistory)
                response.setStatus(LogCode.RC_SUCCESS)
            } else {
                historyDao.insertHistory(baseHistory)
                response.setStatus(LogCode.RC_SUCCESS)
            }
        } else {
            response.resultCode = responseByUserId.resultCode
            response.resultMessage = responseByUserId.resultMessage
        }
        return response
    }

    override fun queryHistoryByUser(userId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<HistoryResponse>> {
        val response = BaseResponse<ListBaseData<HistoryResponse>>()
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(userId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
            PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
            val pageInfo = PageInfo(historyDao.queryHistoryByUser(userId))
            if (pageInfo.list.isEmpty()) {
                response.resultCode = LogCode.RC_RESULT_EMPTY.code
                response.resultMessage = LogCode.RC_RESULT_EMPTY.message
                return response
            }
            response.also {
                it.data = ListBaseData<HistoryResponse>().also { l ->
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

    override fun queryHistoryByType(type: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<HistoryResponse>> {
        val response = BaseResponse<ListBaseData<HistoryResponse>>()
        if (StringUtils.isEmpty(type)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(historyDao.queryHistoryByType(type))
        if (pageInfo.list.isEmpty()) {
            response.resultCode = LogCode.RC_RESULT_EMPTY.code
            response.resultMessage = LogCode.RC_RESULT_EMPTY.message
            return response
        }
        response.also {
            it.data = ListBaseData<HistoryResponse>().also { l ->
                l.lists = pageInfo.list
                l.page = pageInfo.pageNum
                l.pageSize = pageInfo.pageSize
                l.totalCount = pageInfo.total.toInt()
            }
            it.setStatus(LogCode.RC_SUCCESS)
        }
        return response
    }

    override fun updateDel(baseHistory: BaseHistory): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (StringUtils.isEmpty(baseHistory.userId) || StringUtils.isEmpty(baseHistory.businessId) ||
                (baseHistory.delStatus != 0 && baseHistory.delStatus != 1)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val responseByUserId = userService.queryUserById(baseHistory.userId)
        if (responseByUserId.resultCode == LogCode.RC_SUCCESS.code) {
            historyDao.updateDel(baseHistory)
            response.setStatus(LogCode.RC_SUCCESS)
        } else {
            response.resultCode = responseByUserId.resultCode
            response.resultMessage = responseByUserId.resultMessage
        }
        return response
    }

}