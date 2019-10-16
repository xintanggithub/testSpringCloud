package com.tson.yd.controller

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.book.BookEntity
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

    @RequestMapping(value = ["/updateBook"], method = [RequestMethod.POST])
    @ApiOperation(value = "更新book", notes = "v1.0.0")
    fun updateBook(@RequestBody insertBootEntity: InsertBootEntity): BaseResponse<String> {
        return bookService.updateBook(insertBootEntity)
    }

    @RequestMapping(value = ["/queryBook"], method = [RequestMethod.GET])
    @ApiOperation(value = "根据用户和bookId查询book", notes = "v1.0.0")
    fun queryBook(@ApiParam(required = true, name = "userId", value = "用户ID")
                  @RequestParam(value = "userId", required = true) userId: String,
                  @ApiParam(required = true, name = "bookId", value = "bookID")
                  @RequestParam(value = "bookId", required = true) bookId: String): BaseResponse<BookEntity> {
        return bookService.queryBook(userId, bookId)
    }

    @RequestMapping(value = ["/queryBooksByUser"], method = [RequestMethod.GET])
    @ApiOperation(value = "查询用户所有的book", notes = "v1.0.0")
    fun queryBooksByUser(@ApiParam(required = true, name = "userId", value = "用户ID")
                         @RequestParam(value = "userId", required = true) userId: String,
                         @ApiParam(required = true, name = "page", value = "页码")
                         @RequestParam(value = "page", required = true) page: Int,
                         @ApiParam(required = true, name = "pageSize", value = "页码")
                         @RequestParam(value = "pageSize", required = true) pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        return bookService.queryBooksByUser(userId, page, pageSize)
    }

    @RequestMapping(value = ["/queryBookByOpenType"], method = [RequestMethod.GET])
    @ApiOperation(value = "根据开放类型查询列表", notes = "v1.0.0")
    fun queryBookByOpenType(@ApiParam(required = true, name = "isOpen", value = "是否公开，false 私密 true 公开")
                            @RequestParam(value = "isOpen", required = true) isOpen: Boolean,
                            @ApiParam(required = true, name = "page", value = "页码")
                            @RequestParam(value = "page", required = true) page: Int,
                            @ApiParam(required = true, name = "pageSize", value = "页码")
                            @RequestParam(value = "pageSize", required = true) pageSize: Int): BaseResponse<ListBaseData<BookEntity>> {
        return bookService.queryBookByOpenType(isOpen, page, pageSize)
    }

}