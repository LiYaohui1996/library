package main.dao;


//对数据库表library_book_student操作的接口类
import main.dao.domain.BookStudent;

import java.util.List;

public interface BookStudentDao {

    boolean save(BookStudent bean);

    boolean delete(BookStudent bean);

    List<BookStudent> find(BookStudent bean);
}
