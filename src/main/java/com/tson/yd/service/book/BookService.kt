package com.tson.yd.service.book

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.book.InsertBootEntity

interface BookService {

    /**
     * 新增book
     * @param request 新增内容
     */
    fun insertBook(request: InsertBootEntity): BaseResponse<String>

}
