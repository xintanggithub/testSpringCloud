package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.book.InsertBootEntity
import com.tson.yd.service.book.BookService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController("BookController")
@RequestMapping("/v1/book")
@Api(value = "BookController 笔记", description = "book", tags = ["book"])
class BookController {

    @Autowired
    lateinit var bookService: BookService

    @RequestMapping(value = ["/insertBook"], method = [RequestMethod.POST])
    @ApiOperation(value = "新增book", notes = "v1.0.0")
    fun insertBook(@RequestBody insertBootEntity: InsertBootEntity): BaseResponse<String> {
        return bookService.insertBook(insertBootEntity)
    }

    @RequestMapping(value = ["/deleteBook"], method = [RequestMethod.GET])
    @ApiOperation(value = "删除book", notes = "v1.0.0")
    fun deleteBook(@ApiParam(required = true, name = "userId", value = "用户ID")
                   @RequestParam(value = "userId", required = true) userId: String,
                   @ApiParam(required = true, name = "bookId", value = "bookID")
                   @RequestParam(value = "bookId", required = true) bookId: String): BaseResponse<String> {
        return bookService.deleteBook(userId, bookId)
    }

}