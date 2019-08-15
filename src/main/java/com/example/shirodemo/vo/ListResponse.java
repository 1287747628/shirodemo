package com.example.shirodemo.vo;

import java.util.List;

public class ListResponse<T> extends Response {

    private long total = 0;
    private List<T> list;

    public ListResponse(int resultCode) {
        super(resultCode);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
