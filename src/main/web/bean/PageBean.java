package main.web.bean;

import java.util.List;

//分页对象，主要是用来做分页用
public class PageBean<T> {
    private int total;

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
