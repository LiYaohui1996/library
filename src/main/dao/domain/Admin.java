package main.dao.domain;

import java.util.Date;

//实体类,对应数据库中的表library_admin
public class Admin implements java.io.Serializable {
	
    private static final long serialVersionUID = 7061118381898238548L;
    private Integer id;  //主键
    private String ano; //管理员编号
    private String aname; //姓名
    private String password; //密码
    private Date cdate; //创建时间
    private Date udate; //更新时间
    private Integer yn; //删除标记 1是未删除 0是已删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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