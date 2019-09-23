package com.tson.yd.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "列表实体基类")
public class ListBaseData<T> {
    @ApiModelProperty(value = "总数")
    @JsonIgnore
    private int totalCount;
    @ApiModelProperty(value = "页码")
    private int page;
    @ApiModelProperty(value = "条数")
    private int pageSize;
    @ApiModelProperty(value = "列表数据")
    private List<T> lists;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }
}
