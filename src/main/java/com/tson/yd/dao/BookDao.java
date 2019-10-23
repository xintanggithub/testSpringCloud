package com.tson.yd.dao;

import com.tson.yd.model.book.BookEntity;
import com.tson.yd.model.book.InsertBootEntity;
import com.tson.yd.model.book.request.SearchRequest;

import java.util.List;

public interface BookDao {

    /**
     * 新增book
     *
     * @param request 新增内容
     */
    void insertBook(InsertBootEntity request);

    /**
     * 删除book
     *
     * @param userId 用户ID
     * @param bookId bookId
     */
    void deleteBook(String userId, String bookId);

    /**
     * 更新book
     *
     * @param request 更新内容
     */
    void updateBook(InsertBootEntity request);

    /**
     * 查询book
     *
     * @param userId 用户ID
     * @param bookId bookId
     * @return book信息
     */
    BookEntity queryBook(String userId, String bookId);

    /**
     * 查询该用户的所有book
     *
     * @param userId 用户ID
     * @return book列表
     */
    List<BookEntity> queryBooksByUser(String userId);

    /**
     * 查询所有公开的book
     *
     * @param isOpen 是否公开
     * @return book列表
     */
    List<BookEntity> queryBookByOpenType(Integer isOpen);

    /**
     * 搜索所有的book
     * <p>
     * 关键字
     * book 类型 0 保密 1 公开
     *
     * @return book 列表
     */
    List<BookEntity> search(SearchRequest request);

}
