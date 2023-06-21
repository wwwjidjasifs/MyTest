package com.Service.Impl;

import com.Dao.IDepartmentinfoDao;
import com.Pojo.Departmentinfo;
import com.Service.IDepartmentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentinfoServiceImpl implements IDepartmentinfoService {
    @Autowired
    private IDepartmentinfoDao departmentinfoDao;
    @Override
    public List<Departmentinfo> selectAll() {
        return departmentinfoDao.selectDepartmentAll();
    }
    //分页查询 搜索
    @Override
    public List<Departmentinfo> queryAllDep(HashMap<String, Object> maps) {
        return departmentinfoDao.queryAllDep(maps);
    }

    @Override
    public int queryAllRows() {
        return departmentinfoDao.queryAllRows();
    }
    @Override
    public boolean insertDept(Departmentinfo departmentinfo) {
        int i=departmentinfoDao.insertDept(departmentinfo);
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteDept(int departmentid) {
        int i=departmentinfoDao.deleteDept(departmentid);
        return i > 0 ? true:false;
    }

    @Override
    public boolean updateDept(Departmentinfo departmentinfo) {
        int i=departmentinfoDao.updateDept(departmentinfo);
        return i > 0 ? true:false;
    }
}
