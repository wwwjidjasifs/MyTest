package com.Service.Impl;

import com.Dao.IHoildayDao;
import com.Pojo.Holidayinfo;
import com.Service.IHoildayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HoildayServiceImpl implements IHoildayService {
    @Autowired
    private IHoildayDao hoildayDao;
    @Override
    public List<Holidayinfo> queryHolidayHistory(Map<String, Object> maps) {
        return hoildayDao.queryHolidayHistory(maps);
    }

    @Override
    public int queryHistoryRows(int userid) {
        return hoildayDao.queryHistoryRows(userid);
    }
    /**
     * 插入请假单
     * @param holidayinfo
     * @return
     */
    @Override
    public boolean insertApply(Holidayinfo holidayinfo) {
        int i = hoildayDao.insertApply(holidayinfo);
        return i > 0 ? true : false;
    }

    /**
     * 用户修改第一次提交的表单信息
     * @param holidayinfo
     * @return
     */
    @Override
    public boolean updateHoilday(Holidayinfo holidayinfo) {
        int i = hoildayDao.updateTable(holidayinfo);
        return i > 0 ? true : false;
    }
    /**
     * 查询该部门的申请表
     * @param maps
     * @return
     */
    @Override
    public List<Holidayinfo> queryAllHolidayInDept(HashMap<String, Object> maps) {
        return hoildayDao.queryAllHolidayInDept(maps);
    }
    //查询该部门申请表的条数   经理
    @Override
    public int queryAllRowsInDept(int departmentid) {
        return hoildayDao.queryAllRowsInDept(departmentid);
    }
    /**
     * 二级审核
     * @return
     */
    @Override
    public List<Holidayinfo> querySecond(Map<String, Object> maps) {
        return hoildayDao.querySecond(maps);
    }
    //    查询出已经被部门经理审批的假单  人事部 包含自己全部部门和别人审批的
    @Override
    public List<Holidayinfo> querySecondandRen(Map<String, Object> maps) {
        return hoildayDao.querySecondandRen(maps);
    }

    @Override
    public int querySecondRows() {
        return hoildayDao.querySecondRows();
    }
    //查询出所有审批表
    @Override
    public List<Holidayinfo> queryAllHoliday(HashMap<String, Object> maps) {
        return hoildayDao.queryAllHoliday(maps);
    }
    //一级审核
    @Override
    public boolean updateApply(int id, int state) {
        int i = hoildayDao.updateApply(id, state);
        return i > 0 ? true : false;
    }
    //部门经理审批之后 董事长或者人事部经理可以继续审批
    @Override
    public boolean updateIsApply(int id, int isApply) {
        int i = hoildayDao.updateIsApply(id,isApply);
        return i > 0 ? true : false;
    }

    @Override
    public int querySecondandRenRows(int departmentid) {
        return hoildayDao.querySecondandRenRows(departmentid);
    }
}
