package main.dao.impl;

import main.dao.MybatisDao;
import main.dao.BookDao;
import main.dao.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

//对数据库表library_book操作的实现类
@Repository
public class BookDaoImpl extends MybatisDao<Book> implements BookDao {

	//保存图书信息
    @Override
    public boolean save(Book bean) {
        return this.insert("BookMapper.insert", bean);
    }

    //更新图书信息
    @Override
    public boolean update(Book bean) {
        return this.insert("BookMapper.update", bean);
    }
    
    //更新图书在馆的数量，当图书借出是减，图书归还是加
    @Override
    public boolean updateRemain(Book bean) {
        return this.insert("BookMapper.updateRemain", bean);
    }

    //删除图书信息
    @Override
    public boolean delete(Book bean) {
        return this.insert("BookMapper.delete", bean);
    }

    //查询图书信息列表
    @Override
    public List<Book> find(Book bean) {
        return this.queryForList("BookMapper.select", bean);
    }
    
    //根据图书id查询图书信息
    @Override
    public Book findById(Book bean) {
        return this.queryForObject("BookMapper.selectById", bean);
    }

    //根据图书编号查询图书信息
    @Override
    public Book findByBno(Book bean) {
        return this.queryForObject("BookMapper.selectByBno", bean);
    }

}
