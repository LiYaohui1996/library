package main.dao.impl;

import main.dao.SettingDao;
import main.dao.domain.Setting;
import main.dao.MybatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;


//对数据库表library_setting操作的实现类
@Repository
public class SettingDaoImpl extends MybatisDao<Setting> implements SettingDao {


	//修改图书系统设置
    @Override
    public boolean update(Setting bean) {
        return this.insert("SettingMapper.update", bean);
    }

  //查询图书系统设置
    @Override
    public List<Setting> find(Setting bean) {
        return this.queryForList("SettingMapper.select", bean);
    }

  //根据id，查询图书系统设置
    @Override
    public Setting findById(Setting bean) {
        return this.queryForObject("SettingMapper.selectById", bean);
    }
}
