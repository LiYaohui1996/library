package main.dao.domain;

import java.util.Date;

//实体类,对应数据库中的表library_setting
public class Setting implements java.io.Serializable {

    private static final long serialVersionUID = 7061118381898238548L;
    private Integer id;
    private String name; //'设置项名称',
    private Integer value; //设置项值',
    private Date cdate; //创建时间
    private Date udate; //更新时间
    private Integer yn; //删除标记 1是未删除 0是已删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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