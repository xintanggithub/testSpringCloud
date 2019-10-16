package com.tson.yd.model.book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "book实体")
public class BookEntity extends InsertBootEntity {

    @ApiModelProperty(value = "表自增长ID")
    private long id;



}
