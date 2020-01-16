package com.tson.yd.service.user

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.header.HeadEntity

interface HeaderService {

    /**
     * 查询头像列表
     */
    fun queryHead(): BaseResponse<ListBaseData<HeadEntity>>
}
