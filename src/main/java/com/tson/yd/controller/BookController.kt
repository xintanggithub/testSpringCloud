package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.book.InsertBootEntity
import com.tson.yd.service.book.BookService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

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

}