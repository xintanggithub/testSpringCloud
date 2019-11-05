package com.tson.yd.service.game

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.game.GameEntity
import com.tson.yd.model.game.request.GameRequest

interface GameService {
    /**
     * 新增
     *
     * @param request 新增内容
     */
    fun insertGame(request: GameRequest): BaseResponse<String>

    /**
     * 删除
     *
     * @param id 需要删除的ID
     */
    fun deleteGame(id: Int): BaseResponse<String>

    /**
     * 查询所有
     *
     * @return 数据列表
     */
    fun queryList(keyword: String?, page: Int, pageSize: Int): BaseResponse<ListBaseData<GameEntity>>

}
