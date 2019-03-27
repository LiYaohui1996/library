package main.web.controller.admin;

import main.constant.Constant;
import main.dao.AdminDao;
import main.dao.domain.Admin;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//管理员控制器，主要是前端对管理员进行管理的操作
@Controller
@RequestMapping("/admin/admin")
public class AdmAdminController {

    @Resource
    private AdminDao adminDao;

    //跳转到管理员管理的界面
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name); //此处拿登录用户的信息
        model.addAttribute("username", username); //将当前登录用户的信息，放进session中
        return "admin/admin";  //跳转到admin下的admin.jsp
    }

    //查询当前系统管理员的列表信息
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Admin> list(ModelMap model, Admin admin) {
        List<Admin> admins = adminDao.find(admin); //调用adminDao查询数据
        PageBean<Admin> data = new PageBean<Admin>();
        data.setData(admins); //将查询出来的数据，放入分页对象中
        return data;
    }

    //根据id来查询管理员的信息
    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Admin findById(ModelMap model, Admin admin) {
        return adminDao.findById(admin); //直接查询并且返回管理员信息
    }

    //保存管理员信息
    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, Admin admin) {
        Admin adm = adminDao.findByAno(admin); //先查询管理员编号是否存在，如果不存在则保存入库
        if (adm != null) {
            return -1;
        }
        return adminDao.save(admin) ? 1 : 0;
    }

    //更新管理员信息
    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean update(ModelMap model, Admin admin) {
        return adminDao.update(admin);//直接调用dao更新
    }

    //删除管理员信息
    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, Admin admin) {

//        System.out.println("进来了delete");
        return adminDao.delete(admin);
    }
}