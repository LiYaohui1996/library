package main.web.controller.admin;

import main.constant.Constant;
import main.dao.BookTypeDao;
import main.dao.domain.BookType;
import main.dao.BookDao;
import main.dao.domain.Book;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//图书信息控制器
@Controller
@RequestMapping("/admin/book")
public class AdmBookController {

    @Resource
    private BookDao bookDao;

    @Resource
    private BookTypeDao bookTypeDao;

    //跳转到图书管理界面
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name); //获取当前登录的用户名
        List<BookType> bookTypes = bookTypeDao.find(null); //查询所有的图书分类
        model.addAttribute("username", username); 
        model.addAttribute("bookTypes", bookTypes); //将图书分类保存到modelMap中
        return "admin/book"; //返回图书界面
    }

    //获取图书列表信息
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Book> list(ModelMap model, Book book) {
        List<Book> books = bookDao.find(book); 
        PageBean<Book> data = new PageBean<Book>();
        data.setData(books);
        return data;
    }
    
    //根据id获取图书信息
    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Book findById(ModelMap model, Book book) {
        return bookDao.findById(book);
    }

    //保存图书信息
    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, Book book) {
        Book b = bookDao.findByBno(book); 
        if (b != null) {//如果不为空，说明图书已经存在，则不需要重复添加
            return -1;
        }
        return bookDao.save(book) ? 1 : 0;
    }
    
    //更新图书信息
    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean update(ModelMap model, Book book) {
        Book b = bookDao.findById(book);
        book.setTotal(b.getTotal() + (book.getRemain() - b.getRemain()));//获取当前在馆的图书数量，用总数量减去已经借出去的数量
        return bookDao.update(book); //更新操作
    }

    //删除图书信息
    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, Book book) {
        return bookDao.delete(book);
    }
}