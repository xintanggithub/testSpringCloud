package com.tson.yd.service.book

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.book.BookEntity
import com.tson.yd.model.book.InsertBootEntity

interface BookService {

    /**
     * 新增book
     * @param request 新增内容
     */
    fun insertBook(request: InsertBootEntity): BaseResponse<String>

    /**
     * 删除book
     *
     * @param userId 用户ID
     * @param bookId bookId
     */
    fun deleteBook(userId: String, bookId: String): BaseResponse<String>

    /**
     * 更新book
     *
     * @param request 更新内容
     */
    fun updateBook(request: InsertBootEntity): BaseResponse<String>

    /**
     * 查询book
     *
     * @param userId 用户ID
     * @param bookId bookId
     * @return book信息
     */
    fun queryBook(userId: String, bookId: String): BaseResponse<BookEntity>

    /**
     * 查询该用户的所有book
     *
     * @param userId 用户ID
     * @return book列表
     */
    fun queryBooksByUser(userId: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>>

    /**
     * 查询所有公开的book
     *
     * @param isOpen 是否公开
     * @return book列表
     */
    fun queryBookByOpenType(isOpen: Boolean, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>>

    /**
     * 搜索所有的book
     * @param keyword 关键字
     * @param openType book 类型 0 保密 1 公开
     * @return book 列表
     */
    fun search(userId: String?, keyword: String?, openType: Int, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>>

    /**
     * 搜索所有的book
     * @param keyword 关键字
     * @param openType book 类型 0 保密 1 公开
     * @return book 列表
     */
    fun searchPublic(keyword: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>>

    /**
     * 搜索所有的book
     * @param keyword 关键字
     * @param openType book 类型 0 保密 1 公开
     * @return book 列表
     */
    fun searchPrivate(userId: String?, keyword: String, page: Int, pageSize: Int): BaseResponse<ListBaseData<BookEntity>>

}
