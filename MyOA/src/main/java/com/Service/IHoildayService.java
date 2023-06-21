package com.Service;

import com.Pojo.Holidayinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IHoildayService {
    List<Holidayinfo> queryHolidayHistory(Map<String, Object> maps);

    int queryHistoryRows(int userid);
    /**
     * 插入请假单
     * @param holidayinfo
     * @return
     */
    boolean insertApply(Holidayinfo holidayinfo);
    /**
     * 用户修改第一次提交的表单信息
     * @param holidayinfo
     * @return
     */
    boolean updateHoilday(Holidayinfo holidayinfo);
    /**
     * 查询该部门的申请表
     * @param maps
     * @return
     */
    List<Holidayinfo> queryAllHolidayInDept(HashMap<String, Object> maps);

    int queryAllRowsInDept(int departmentid);

    /**
     * 二级审核
     * @return
     */
    List<Holidayinfo> querySecond(Map<String, Object> maps);
    List<Holidayinfo> querySecondandRen(Map<String, Object> maps);
    //查出二级审核的条数
    int querySecondRows();
    //查询出所有审批表
    List<Holidayinfo> queryAllHoliday(HashMap<String, Object> maps);

    /**
     * 请假单审批
     * @param id
     * @param state
     * @return
     */
    boolean updateApply(int id, int state);


    /**
     * 二级审核修改状态
     * @param id
     * @param isApply
     * @return
     */
    boolean updateIsApply( int id, int isApply);

    int querySecondandRenRows(int departmentid);
}
