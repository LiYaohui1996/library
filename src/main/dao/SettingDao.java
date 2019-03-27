package main.dao;


//对数据库表library_setting操作的接口类
import main.dao.domain.Setting;

import java.util.List;

public interface SettingDao {

    boolean update(Setting bean);

    List<Setting> find(Setting bean);

    Setting findById(Setting bean);
}
