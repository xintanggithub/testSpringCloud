package com.tson.yd.service.book

import com.tson.yd.base.BaseResponse
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


}
