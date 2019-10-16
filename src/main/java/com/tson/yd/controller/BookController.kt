package com.tson.yd.controller

import com.tson.yd.service.book.BookService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("BookController")
@RequestMapping("/v1/book")
@Api(value = "BookController 笔记", description = "book", tags = ["book"])
class BookController{

    @Autowired
    lateinit var bookService: BookService

}