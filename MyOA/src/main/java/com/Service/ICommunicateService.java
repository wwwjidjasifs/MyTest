package com.Service;

import com.Pojo.Communicateinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICommunicateService {
    //查询所有会议申请列表.当前登录的人
    List<Communicateinfo> queryMineComm(Map<String,Object> maps);

    String selectRoomNameByRoomId(int roomid);

    String selectUserNameByUserId(int userid);

    int queryMineRows(int userid);

    boolean insertMessage(Communicateinfo communicateinfo);

    boolean updateMessage(Communicateinfo communicateinfo);
    //查询审批列表
    List<Communicateinfo> queryAllComm(HashMap<String, Object> maps);

    int queryAllRows();
    //修改会议审批的状态
    boolean updateCommunicateState(Integer comid, Integer state);
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
    boolean updateIsRoomApplyState(Integer comid, int isApply);

    /**
     * 修改会议审批状态和会议室占用状态
     * @param comid
     * @param state
     * @return
     */
    boolean updateCommStateAndRoomIsApply(Integer comid, Integer state);
}
