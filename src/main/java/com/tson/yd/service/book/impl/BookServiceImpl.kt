package com.tson.yd.service.book.impl

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.LogCode
import com.tson.yd.dao.BookDao
import com.tson.yd.model.book.InsertBootEntity
import com.tson.yd.service.book.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : BookService {

    @Autowired
    lateinit var bookDao: BookDao

    override fun insertBook(request: InsertBootEntity): BaseResponse<String> {
        val response = BaseResponse<String>()
        //新增用户登录校验、参数校验
        bookDao.insertBook(request)
        response.run {
            setStatus(LogCode.RC_SUCCESS)
        }
        return response
    }

}
