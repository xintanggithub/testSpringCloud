package com.tson.yd.service.book.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.BookDao
import com.tson.yd.model.book.BookAllEntity
import com.tson.yd.model.book.BookEntity
import com.tson.yd.model.book.InsertBootEntity
import com.tson.yd.model.book.request.SearchRequest
import com.tson.yd.service.book.BookService
import com.tson.yd.service.user.impl.UserServiceImpl
import com.tson.yd.utils.CharUtils
import org.apache.commons.beanutils.BeanUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : BookService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BookServiceImpl::class.java)
    }

    @Autowired
    lateinit var bookDao: BookDao

    @Autowired
    lateinit var userServiceImpl: UserServiceImpl

    override fun insertBook(request: InsertBootEntity): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (request.userId == null || request.userId.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
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

    override fun deleteBook(userId: String, bookId: String): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (userId.isEmpty() || bookId.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
        val queryUser = userServiceImpl.queryUserById(userId)
        if (queryUser.resultCode != LogCode.RC_SUCCESS.code) {
            return response.also {
                it.resultCode = queryUser.resultCode
                it.resultMessage = queryUser.resultMessage
            }
        }

        bookDao.deleteBook(userId, bookId);
        response.run {
            setStatus(LogCode.RC_SUCCESS)
        }
        return response
    }

    override fun queryBook(userId: String?, bookId: String): BaseResponse<BookEntity> {
        val response = BaseResponse<BookEntity>()
//        if (userId.isEmpty() || bookId.isEmpty()) {
        if (bookId.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
//        val queryUser = userServiceImpl.queryUserById(userId)
//        if (queryUser.resultCode != LogCode.RC_SUCCESS.code) {
//            return response.also {
//                it.resultCode = queryUser.resultCode
//                it.resultMessage = queryUser.resultMessage
//            }
//        }


        return response.also {
            it.setStatus(LogCode.RC_SUCCESS)
            it.data = bookDao.queryBook(userId, bookId)
        }
    }

    override fun updateBook(request: InsertBootEntity): BaseResponse<String> {
        val response = BaseResponse<String>()
        if (request.userId.isEmpty() || request.bookId.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
        val queryBook = queryBook(request.userId, request.bookId)
        if (queryBook.resultCode != LogCode.RC_SUCCESS.code) {
            return response.also {
                it.resultCode = queryBook.resultCode
                it.resultMessage = queryBook.resultMessage
            }
        }
        bookDao.updateBook(request)
        response.run {
            setStatus(LogCode.RC_SUCCESS)
        }
        return response
    }

    override fun queryBooksByUser(userId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        val response = BaseResponse<ListBaseData<BookEntity>>()
        if (userId.isEmpty()) {
            return response.also {
                it.setStatus(LogCode.RC_PARAMETER_ERROR)
            }
        }
        val queryUser = userServiceImpl.queryUserById(userId)
        if (queryUser.resultCode != LogCode.RC_SUCCESS.code) {
            return response.also {
                it.resultCode = queryUser.resultCode
                it.resultMessage = queryUser.resultMessage
            }
        }
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(bookDao.queryBooksByUser(userId))
        if (pageInfo.list.isEmpty()) {
            response.resultCode = LogCode.RC_RESULT_EMPTY.code
            response.resultMessage = LogCode.RC_RESULT_EMPTY.message
            return response
        }
        return response.also {
            it.data = ListBaseData<BookEntity>().also { l ->
                l.lists = pageInfo.list
                l.page = pageInfo.pageNum
                l.pageSize = pageInfo.pageSize
                l.totalCount = pageInfo.total.toInt()
            }
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun queryBookAllByUser(userId: String?, keyword: String?, openType: Int, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookAllEntity>> {
        val response = BaseResponse<ListBaseData<BookAllEntity>>()
        val result = search(userId, keyword, openType, page, pageSize)
        if (result.resultCode == LogCode.RC_SUCCESS.code) {
            val list = result.data.lists
            val newList = mutableListOf<BookAllEntity>()
            list.forEach { item ->
                val queryUser = userServiceImpl.queryUserById(item.userId)
                val itemData = BookAllEntity()
                BeanUtils.copyProperties(itemData, item)
                if (queryUser.resultCode == LogCode.RC_SUCCESS.code) {
                    itemData.run {
                        userName = queryUser.data.userName
                        userHead = queryUser.data.img
                    }
                }

                newList.add(itemData)
            }
            response.data = ListBaseData<BookAllEntity>().also {
                it.lists = newList
                it.page = result.data.page
                it.pageSize = result.data.pageSize
                it.totalCount = result.data.totalCount
            }
            response.setStatus(LogCode.RC_SUCCESS)
        } else {
            response.resultCode = result.resultCode
            response.resultMessage = result.resultMessage
        }
        return response
    }


    override fun queryBookByOpenType(isOpen: Boolean, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        val response = BaseResponse<ListBaseData<BookEntity>>()
        //默认隐私
        val type = if (isOpen) {
            1
        } else {
            0
        }
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(bookDao.queryBookByOpenType(type))

        if (pageInfo.list.isEmpty()) {
            response.resultCode = LogCode.RC_RESULT_EMPTY.code
            response.resultMessage = LogCode.RC_RESULT_EMPTY.message
            return response
        }
        return response.also {
            it.data = ListBaseData<BookEntity>().also { l ->
                l.lists = pageInfo.list
                l.page = pageInfo.pageNum
                l.pageSize = pageInfo.pageSize
                l.totalCount = pageInfo.total.toInt()
            }
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

    override fun searchPublic(keyword: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        return search("", keyword, 1, page, pageSize)
    }

    override fun searchPrivate(userId: String?, keyword: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        return search(userId, keyword, 0, page, pageSize)
    }

    override fun search(userId: String?, keyword: String?, openType: Int, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        LOGGER.info("userId=$userId  keyword=$keyword  openType=$openType")
        val response = BaseResponse<ListBaseData<BookEntity>>()
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val dt = SearchRequest().also {
            it.keyword = keyword
            it.openType = openType
            if (!userId.isNullOrEmpty()) {
                it.userId = userId
            }
        }
        val pageInfo = PageInfo(bookDao.search(dt))
        if (pageInfo.list.isEmpty()) {
            response.resultCode = LogCode.RC_RESULT_EMPTY.code
            response.resultMessage = LogCode.RC_RESULT_EMPTY.message
            return response
        }
        return response.also {
            it.data = ListBaseData<BookEntity>().also { l ->
                l.lists = pageInfo.list
                l.page = pageInfo.pageNum
                l.pageSize = pageInfo.pageSize
                l.totalCount = pageInfo.total.toInt()
            }
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }

}
