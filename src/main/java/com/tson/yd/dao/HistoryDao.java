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

    /**
     * 更新记录数据
     */
    void updateAll(BaseHistory baseHistory);

    /**
     * 通过所有条件查询历史记录
     * <br/>主要用于校验是否已有该历史记录
     */
    List<HistoryResponse> queryHistoryByAll(BaseHistory baseHistory);

}
