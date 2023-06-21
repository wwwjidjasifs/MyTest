package com.Dao;

import com.Pojo.Departmentinfo;

import java.util.HashMap;
import java.util.List;

public interface IDepartmentinfoDao {
    //查询所有部门
    List<Departmentinfo> selectDepartmentAll();
    //分页查询 搜索
    List<Departmentinfo> queryAllDep(HashMap<String, Object> maps);
    /**
     * 查询所有的部门总数
     * @return
     */
    int queryAllRows();
    //插入部门
    int insertDept(Departmentinfo departmentinfo);

    int deleteDept(int departmentid);

    int updateDept(Departmentinfo departmentinfo);
}
