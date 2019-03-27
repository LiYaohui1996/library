package main.dao.impl;

import main.dao.AdminDao;
import main.dao.domain.Admin;
import main.dao.MybatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

//对数据库表library_admin操作的实现类
@Repository
public class AdminDaoImpl extends MybatisDao<Admin> implements AdminDao {

	//保存数据到library_admin表中
    @Override
    public boolean save(Admin bean) {
        return this.insert("AdminMapper.insert", bean);
    }

    //更新数据到library_admin表中
    @Override
    public boolean update(Admin bean) {
        return this.insert("AdminMapper.update", bean);
    }

    //删除管理员数据
    @Override
    public boolean delete(Admin bean) {
        System.out.println("到dao");
        return this.update("AdminMapper.delete", bean);
    }

    //查询管理员列表信息
    @Override
    public List<Admin> find(Admin bean) {
        return this.queryForList("AdminMapper.select", bean);
    }

    //根据ID查询管理员信息
    @Override
    public Admin findById(Admin bean) {
        return this.queryForObject("AdminMapper.selectById", bean);
    }
    
    //根据管理员编号查询管理员信息
    @Override
    public Admin findByAno(Admin bean) {
        return this.queryForObject("AdminMapper.selectByAno", bean);
    }

}
