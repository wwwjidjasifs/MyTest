package com.Dao;

import com.Pojo.Holidayinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IHoildayDao {
    List<Holidayinfo> queryHolidayHistory(Map<String, Object> maps);

    int queryHistoryRows(int userid);
    /**
     * 插入请假单
     * @param holidayinfo
     * @return
     */
    int insertApply(Holidayinfo holidayinfo);

    int updateTable(Holidayinfo holidayinfo);
    /**
     * 查询该部门的申请表
     * @param maps
     * @return
     */
    List<Holidayinfo> queryAllHolidayInDept(HashMap<String, Object> maps);

    int queryAllRowsInDept(int departmentid);
    //二级审核
    List<Holidayinfo> querySecond(Map<String, Object> maps);
//    查询出已经被部门经理审批的假单  人事部 包含自己全部部门和别人审批的
    List<Holidayinfo> querySecondandRen(Map<String, Object> maps);

    int querySecondRows();
    //查询人事部包括二级审核的条数
    int querySecondandRenRows(int departmentid);

    List<Holidayinfo> queryAllHoliday(HashMap<String, Object> maps);
    //一级审核
    int updateApply(@Param("id") int id,@Param("state") int state);
    //部门经理审批之后 董事长或者人事部经理可以继续审批
    int updateIsApply(@Param("id") int id, @Param("isApply") int isApply);
}
