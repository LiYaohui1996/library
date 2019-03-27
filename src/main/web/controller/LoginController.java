package main.web.controller;

import main.constant.Constant;
import main.dao.AdminDao;
import main.dao.StudentDao;
import main.dao.domain.Admin;
import main.dao.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
//登录控制器
@Controller
@RequestMapping("/")
public class LoginController {

    @Resource
    private StudentDao studentDao;

    @Resource
    private AdminDao adminDao;

    //跳转到登录界面
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "login";
    }

    //用户登录操作
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public int studentLogin(HttpServletRequest request, String username,
                            String password, int role) {
        if (role == 1) { //学生登录
            Student student = new Student();
            student.setSno(username);
            Student stu = studentDao.findBySno(student); //根据学号查询学生信息
            if (stu == null) { //如果为空，则说明不存在
                return -1;
            } else {
                if (password.equals(stu.getPassword())) { //如果用户名存在，则匹配密码，如果密码正确，则将用户信息保存到session，并且返回1
                    request.getSession()
                            .setAttribute(Constant.student_login_session_name,
                                    stu.getSno());
                    request.getSession().setAttribute(
                            Constant.user_id, stu.getId());
                    request.getSession().setAttribute(
                            Constant.user_name,
                            stu.getSname());
                    return 1;
                } else {
                    return -2;
                }
            }
        } else if (role == 2) {// 管理员登录
            Admin admin = new Admin();

            admin.setAno(username);
            Admin adm = adminDao.findByAno(admin); //根据管理员账号，查询管理员信息
            if (adm == null) {//如果为空，则说明不存在
                return -1;
            } else {
                if (password.equals(adm.getPassword())) {//如果用户名存在，则匹配密码，如果密码正确，则将用户信息保存到session，并且返回1
                    request.getSession()
                            .setAttribute(Constant.admin_login_session_name,
                                    adm.getAno());
                    request.getSession().setAttribute(
                            Constant.user_id, adm.getId());
                    request.getSession().setAttribute(
                            Constant.user_name,
                            adm.getAname());
                    return 1;
                } else {
                    return -2;
                }
            }
        } else {
            return 0;
        }
    }

    //退出登录，并且销毁登录信息
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute(
                Constant.student_login_session_name, null);
        request.getSession().setAttribute(
                Constant.admin_login_session_name, null);
        return "login";
    }
}
