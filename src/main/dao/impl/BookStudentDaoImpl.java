package main.dao.impl;

import main.dao.MybatisDao;
import main.dao.BookStudentDao;
import main.dao.domain.BookStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

//对数据库表library_book_student操作的实现类
@Repository
public class BookStudentDaoImpl extends MybatisDao<BookStudent> implements BookStudentDao {

	//保存图书与学生的借还关系
    @Override
    public boolean save(BookStudent bean) {
        return this.insert("BookStudentMapper.insert", bean);
    }

    //删除图书与学生的借还关系
    @Override
    public boolean delete(BookStudent bean) {
        return this.insert("BookStudentMapper.delete", bean);
    }

    //查询图书与学生的借还关系
    @Override
    public List<BookStudent> find(BookStudent bean) {
        return this.queryForList("BookStudentMapper.select", bean);
    }

}
