package main.dao.domain;

import java.util.Date;

//实体类,对应数据库中的表library_book_type
public class BookType implements java.io.Serializable {
    private static final long serialVersionUID = -4714427866396004820L;
    private Integer id;//主键
    private String tname;//类型名称
    private Date cdate; //创建时间
    private Date udate; //更新时间
    private Integer yn; //删除标记 1是未删除 0是已删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}