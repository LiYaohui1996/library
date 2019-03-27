package main.dao.impl;

import main.dao.BookTypeDao;
import main.dao.domain.BookType;
import main.dao.MybatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

//对数据库表library_book_type操作的实现类
@Repository
public class BookTypeDaoImpl extends MybatisDao<BookType> implements BookTypeDao {

	//保存图书类型信息
    @Override
    public boolean save(BookType bean) {
        return this.insert("BookTypeMapper.insert", bean);
    }

    //更新图书类型信息
    @Override
    public boolean update(BookType bean) {
        return this.update("BookTypeMapper.update", bean);
    }

    //删除图书类型信息
    @Override
    public boolean delete(BookType bean) {
        return this.insert("BookTypeMapper.delete", bean);
    }

    //查询所有的图书类型信息
    @Override
    public List<BookType> find(BookType bean) {
        return this.queryForList("BookTypeMapper.select", bean);
    }

}
