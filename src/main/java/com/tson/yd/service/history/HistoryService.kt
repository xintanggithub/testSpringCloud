package com.tson.yd.service.history

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.history.BaseHistory
import com.tson.yd.model.history.HistoryResponse

interface HistoryService {
    /**
     * 新增历史记录
     *
     * @param baseHistory 历史记录信息
     */
    fun insertHistory(baseHistory: BaseHistory): BaseResponse<String>

    /**
     * 查询对应用户ID的历史记录列表
     *
     * @param userId 用户ID
     * @return 历史记录列表
     */
    fun queryHistoryByUser(userId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<HistoryResponse>>

    /**
     * 查询对应类型被浏览的历史
     *
     * @param type 类型
     * @return 历史记录列表
     */
    fun queryHistoryByType(type: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<HistoryResponse>>

    /**
     * 更新删除状态
     *
     * @param baseHistory 更新信息
     */
    fun updateDel(baseHistory: BaseHistory): BaseResponse<String>
}