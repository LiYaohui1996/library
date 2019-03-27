package main.web.controller.admin;

import main.constant.Constant;
import main.dao.BookStudentDao;
import main.dao.StudentDao;
import main.dao.domain.Setting;
import main.dao.domain.Student;
import main.util.DateUtils;
import main.dao.BookDao;
import main.dao.SettingDao;
import main.dao.domain.Book;
import main.dao.domain.BookStudent;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//借书控制器
@Controller
@RequestMapping("/admin/borrow")
public class AdmBorrowController {

    @Resource
    private BookStudentDao bookStudentDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private BookDao bookDao;

    @Resource
    private SettingDao settingDao;

    //跳转到图书借阅界面
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/borrow";
    }

    //查询图书已经被借阅的信息
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<BookStudent> list(ModelMap model, BookStudent bookStudent) {
        PageBean<BookStudent> data = new PageBean<BookStudent>();

        /*if (null == bookStudent.getSno() || "".equals(bookStudent.getSno())){
            data.setData(new ArrayList<BookStudent>());
            return data;
        }*/
        List<BookStudent> bookStudents = bookStudentDao.find(bookStudent);
        data.setData(bookStudents);
//        System.out.println("进list");
        return data;
    }

    //保存图书借阅的信息，也就是借书操作
    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, BookStudent bookStudent) {

        Student student = new Student();
        student.setSno(bookStudent.getSno());
        student = studentDao.findBySno(student); //根据学号查询学生是否存在
        if (student == null) {
            return -1;
        }

        Book book = new Book();
        book.setBno(bookStudent.getBno()); 
        book = bookDao.findByBno(book);//根据图书编号查询图书是否存在
        if (book == null) {
            return -2;
        }

        if (book.getRemain() <= 0) {
            return -3;
        }

        BookStudent bs1 = new BookStudent();
        bs1.setSid(student.getId());
        bs1.setBid(book.getId());
        List<BookStudent> bss1 = bookStudentDao.find(bs1); //查询当前学号的学生是否已经借阅了这本图书
        if (!CollectionUtils.isEmpty(bss1)) {
            return -4;
        }


        Setting setting1 = new Setting();
        setting1.setId(Constant.keep_book_max_amount_id);
        setting1 = settingDao.findById(setting1); //查询当前系统的对每个人能借书的数量

        BookStudent bs2 = new BookStudent();
        bs2.setSid(student.getId());
        List<BookStudent> bss2 = bookStudentDao.find(bs2);
        if (!CollectionUtils.isEmpty(bss2) && bss2.size() > setting1.getValue()) { //如果超过数量的限制，则返回失败
            return -5;
        }

        Setting setting2 = new Setting();
        setting2.setId(Constant.keep_book_max_days_id);
        setting2 = settingDao.findById(setting2);//查询当前系统借书天数的限制

        bookStudent.setSid(student.getId());
        bookStudent.setBid(book.getId());
        bookStudent.setBdate(new Date());
        bookStudent.setRdate(DateUtils.addDay(new Date(), setting2.getValue()));//更新最晚需要还书的日期
        boolean rs1 = bookStudentDao.save(bookStudent);  //保存借书记录

        Book b = new Book();
        b.setId(book.getId());
        b.setRemain(book.getRemain() - 1); //将图书的馆藏数量减1
        boolean rs2 = bookDao.updateRemain(b);

        return rs1 && rs2 ? 1 : 0;
    }

}