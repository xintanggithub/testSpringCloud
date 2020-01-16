package com.tson.yd.service.user.impl

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.HeaderDao
import com.tson.yd.model.header.HeadEntity
import com.tson.yd.service.user.HeaderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = "HeaderService")
class HeaderServiceImpl : HeaderService {

    @Autowired
    private lateinit var headerDao: HeaderDao

    override fun queryHead(): BaseResponse<ListBaseData<HeadEntity>> {
        val response = BaseResponse<ListBaseData<HeadEntity>>()
        response.setStatus(LogCode.RC_SUCCESS)
        val list = ListBaseData<HeadEntity>()
        list.lists = headerDao.queryHead()
        list.page = 1
        list.pageSize = list.lists.size
        list.totalCount = list.pageSize
        response.data = list
        return response
    }

}