package com.tson.yd.dao;

import com.tson.yd.model.history.BaseHistory;
import com.tson.yd.model.history.HistoryResponse;

import java.util.List;

public interface HistoryDao {

    /**
     * 新增历史记录
     *
     * @param baseHistory 历史记录信息
     */
    void insertHistory(BaseHistory baseHistory);

    /**
     * 查询对应用户ID的历史记录列表
     *
     * @param userId 用户ID
     * @return 历史记录列表
     */
    List<HistoryResponse> queryHistoryByUser(String userId);

    /**
     * 查询对应类型被浏览的历史
     *
     * @param type 类型
     * @return 历史记录列表
     */
    List<HistoryResponse> queryHistoryByType(String type);

    /**
     * 更新删除状态
     *
     * @param baseHistory 更新信息
     */
    void updateDel(BaseHistory baseHistory);

}
