package main.web.controller.admin;

import main.constant.Constant;
import main.dao.SettingDao;
import main.dao.domain.Setting;
import main.web.bean.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//系统设置控制器
@Controller
@RequestMapping("/admin/setting")
public class AdmSettingController {

    @Resource
    private SettingDao settingDao;

    //跳转到系统设置界面
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/setting";
    }

    //查询当前系统设置的信息
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Setting> list(ModelMap model, Setting setting) {
        List<Setting> settings = settingDao.find(setting);
        PageBean<Setting> data = new PageBean<Setting>();
        data.setData(settings);
        return data;
    }

    //根据id查询系统设置的详情
    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Setting findById(ModelMap model, Setting setting) {
        return settingDao.findById(setting);
    }

    //更新系统设置信息
    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean update(ModelMap model, Setting setting) {
        return settingDao.update(setting);
    }

}