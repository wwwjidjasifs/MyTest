package com.Dao;

import com.Pojo.Communicateinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICommunicateDao {
    //查询所有会议申请列表
    List<Communicateinfo> queryMineComm(Map<String, Object> maps);

    String selectRoomNameByRoomId(int roomid);

    String selectUserNameByUserId(int userid);

    int queryMineRows(int userid);
    //添加会议申请
    boolean insertMessage(Communicateinfo communicateinfo);
    //修改会议修改
    boolean updateMessage(Communicateinfo communicateinfo);
    //查询审批列表
    List<Communicateinfo> queryAllComm(HashMap<String, Object> maps);

    int queryAllRows();
    //修改会议审批的状态
    boolean updateCommunicateState(@Param("comid") Integer comid, @Param("state") Integer state);
    /**
     * 根据comid查询会议室申请状态
     * @param comid
     * @return
     */
    int selectRoomIsApply(Integer comid);
    /**
     * 审批完会议室之后同步修改room表isApply的状态
     * @param comid
     * @param isApply
     * @return
     */
    boolean updateIsRoomApplyState(@Param("comid") Integer comid, @Param("isApply") int isApply);
}
