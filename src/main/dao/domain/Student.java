package main.dao.domain;

import java.util.Date;

//实体类,对应数据库中的表library_student
public class Student implements java.io.Serializable {
	private static final long serialVersionUID = 8442899157404726187L;
	private Integer id;
	private String sno; //学号
	private String sname;//学生名称
	private String password;//密码
	private String passwordNew;//新密码
    private Date cdate; //创建时间
    private Date udate; //更新时间
	private Integer yn; //删除标记 1是未删除 0是已删除

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
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