package com.tson.yd.model.game;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "game实体")
public class GameResponseEntity extends GameEntity {

    @ApiModelProperty(value = "是否已收藏 1已收藏  0未收藏")
    private int collection;

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }
}
