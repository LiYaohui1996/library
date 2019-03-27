package main.web.controller.admin;

import main.constant.Constant;
import main.dao.StudentDao;
import main.dao.domain.Student;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//学生管理控制器
@Controller
@RequestMapping("/admin/student")
public class AdmStudentController {

    @Resource
    private StudentDao studentDao;

    //跳转到学生管理界面
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/student";
    }

    //查询学生信息列表
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Student> list(ModelMap model, Student student) {
        List<Student> students = studentDao.find(student);
        PageBean<Student> data = new PageBean<Student>();
        data.setData(students);
        return data;
    }

    //根据学生id查询学生信息
    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Student findById(ModelMap model, Student student) {
        return studentDao.findById(student);
    }

    //保存学生信息
    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, Student student) {
        Student stu = studentDao.findBySno(student); //先查询当前学号的的学生是否存在
        if (stu != null) { //如果不为空则说明已经添加
            return -1;
        }
        return studentDao.save(student) ? 1 : 0;
    }

    //更新学生信息
    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean update(ModelMap model, Student student) {
        return studentDao.update(student);
    }

    //删除学生信息
    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, Student student) {
        return studentDao.delete(student);
    }
}