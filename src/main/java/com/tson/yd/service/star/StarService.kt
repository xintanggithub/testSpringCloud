package com.tson.yd.service.star

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.star.BaseStar
import com.tson.yd.model.star.StarResponse

interface StarService {

    /**
     * 插入评星数据
     *
     * @param baseStar 评星基本数据
     */
    fun insertStar(baseStar: BaseStar): BaseResponse<String>

    /**
     * 更新评星状态
     */
    fun updateStatus(baseStar: BaseStar): BaseResponse<String>

    /**
     * 查看被点赞数量
     *
     * @param userId 被点赞人的ID
     * @return 被点赞数
     */
    fun queryBStar(userId: String): BaseResponse<Int>

    /**
     * 查看自己点赞过的人
     *
     * @param starUserId 点赞人的ID
     * @return 点赞数
     */
    fun queryWStar(starUserId: String): BaseResponse<Int>

    /**
     * 查询评论我的评星列表
     *
     * @param userId 用户ID
     * @return 评星列表
     */
    fun queryStarList(userId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<StarResponse>>

    /**
     * 查询我的评论评星列表
     *
     * @param starUserId 点赞用户ID
     * @return 评星列表
     */
    fun queryWStarList(starUserId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<StarResponse>>

    /**
     * 查看该点赞的重复数
     */
    fun queryRetryStar(baseStar: BaseStar): BaseResponse<Int>
}