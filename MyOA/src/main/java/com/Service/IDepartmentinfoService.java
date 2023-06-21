package com.Service;

import com.Pojo.Departmentinfo;

import java.util.HashMap;
import java.util.List;

public interface IDepartmentinfoService {
    //查询所有部门
    List<Departmentinfo> selectAll();
    //查询所有部门 进行分页 或者搜索
    List<Departmentinfo> queryAllDep(HashMap<String, Object> maps);
    //查询总条数
    int queryAllRows();
    //插入数据
    boolean insertDept(Departmentinfo departmentinfo);

    boolean updateDept(Departmentinfo departmentinfo);

    boolean deleteDept(int departmentid);
}
