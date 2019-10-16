package com.tson.yd.service.book.impl

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.LogCode
import com.tson.yd.dao.BookDao
import com.tson.yd.model.book.InsertBootEntity
import com.tson.yd.service.book.BookService
import com.tson.yd.service.user.impl.UserServiceImpl
import com.tson.yd.utils.CharUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : BookService {

    @Autowired
    lateinit var bookDao: BookDao

    @Autowired
    lateinit var userServiceImpl: UserServiceImpl

    override fun insertBook(request: InsertBootEntity): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (request.userId.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_USER_NONE)
            }
        }
        val queryUser = userServiceImpl.queryUserById(request.userId)
        if (queryUser.resultCode != LogCode.RC_SUCCESS.code) {
            return response.also {
                it.resultCode = queryUser.resultCode
                it.resultMessage = queryUser.resultMessage
            }
        }
        request.bookId = CharUtils.getBookId()
        bookDao.insertBook(request)
        response.run {
            setStatus(LogCode.RC_SUCCESS)
        }
        return response
    }

}
