package main.web.controller.admin;

import main.constant.Constant;
import main.dao.BookStudentDao;
import main.dao.domain.BookStudent;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
//图书借阅查询控制器
@Controller
@RequestMapping("/admin/borrowInfo")
public class AdmBorrowInfoController {

    @Resource
    private BookStudentDao bookStudentDao;

    //跳转到图书借阅界面
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/borrow-info";
    }

    //查询当前图书借阅的所有信息
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<BookStudent> list(ModelMap model, BookStudent bookStudent) {
        List<BookStudent> bookStudents = bookStudentDao.find(bookStudent);
        PageBean<BookStudent> data = new PageBean<BookStudent>();
        data.setData(bookStudents);
        return data;
    }
}