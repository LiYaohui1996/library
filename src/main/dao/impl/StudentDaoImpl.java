package main.dao.impl;

import main.dao.MybatisDao;
import main.dao.StudentDao;
import main.dao.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

//对数据库表library_student操作的实现类
@Repository
public class StudentDaoImpl extends MybatisDao<Student> implements StudentDao {

	//保存学生信息
    @Override
    public boolean save(Student bean) {
        return this.insert("StudentMapper.insert", bean);
    }

  //更新学生信息
    @Override
    public boolean update(Student bean) {
        return this.insert("StudentMapper.update", bean);
    }

  //删除学生信息
    @Override
    public boolean delete(Student bean) {
        return this.insert("StudentMapper.delete", bean);
    }

  //查询学生信息列表
    @Override
    public List<Student> find(Student bean) {
        return this.queryForList("StudentMapper.select", bean);
    }

  //根据id查询学生信息
    @Override
    public Student findById(Student bean) {
        return this.queryForObject("StudentMapper.selectById", bean);
    }

  //根据学号查询学生信息
    @Override
    public Student findBySno(Student bean) {
        return this.queryForObject("StudentMapper.selectBySno", bean);
    }

}
