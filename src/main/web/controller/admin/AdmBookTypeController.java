package main.web.controller.admin;

import main.constant.Constant;
import main.dao.BookTypeDao;
import main.dao.domain.BookType;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//图书类型控制器
@Controller
@RequestMapping("/admin/bookType")
public class AdmBookTypeController {

    @Resource
    private BookTypeDao bookTypeDao;

    //跳转到图书类型界面
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/book-type";
    }

    //查询所有的图书类型信息，并且将数据保存到分页对象中，然后前端取出分页对象的数据，显示在前端界面
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<BookType> list(ModelMap model, BookType bookType) {
        List<BookType> bookTypes = bookTypeDao.find(bookType);
        PageBean<BookType> data = new PageBean<BookType>();
        data.setData(bookTypes);
        return data;
    }

    //根据id获取某一个图书分类信息
    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public BookType findById(ModelMap model, BookType bookType) {
        List<BookType> bookTypes = bookTypeDao.find(bookType);
        if (CollectionUtils.isEmpty(bookTypes)) {
            return null;
        } else {
            return bookTypes.get(0);
        }
    }

    //保存图书分类信息
    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, BookType bookType) {
        List<BookType> bookTypes = bookTypeDao.find(bookType); //查询图书分类
        if (!CollectionUtils.isEmpty(bookTypes)) { //如果不为空，说明已经存在，则不需要重复添加
            return -1;
        }
        return bookTypeDao.save(bookType) ? 1 : 0; //保存操作，成功返回1 ，失败返回0
    }

    //更新图书分类操作
    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public int update(ModelMap model, BookType bookType) {
        BookType bt = new BookType();
        bt.setTname(bookType.getTname());
        List<BookType> bookTypes = bookTypeDao.find(bt);
        if (!CollectionUtils.isEmpty(bookTypes)) { 
            if (bookType.getId() != bookTypes.get(0).getId()) { //
                return -1;
            }
        }
        return bookTypeDao.update(bookType) ? 1 : 0;
    }

     //删除图书分类操作
    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, BookType bookType) {
        return bookTypeDao.delete(bookType);
    }


}