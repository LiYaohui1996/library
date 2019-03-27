package main.dao;


//对数据库表library_book_type操作的接口类
import main.dao.domain.BookType;

import java.util.List;

public interface BookTypeDao {

    boolean save(BookType bean);

    boolean update(BookType bean);

    boolean delete(BookType bean);

    List<BookType> find(BookType bean);

}
