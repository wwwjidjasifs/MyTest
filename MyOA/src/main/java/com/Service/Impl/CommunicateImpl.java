package com.Service.Impl;

import com.Dao.ICommunicateDao;
import com.Pojo.Communicateinfo;
import com.Service.ICommunicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunicateImpl implements ICommunicateService {
    @Autowired
    private ICommunicateDao communicateDao;

    @Override
    public List<Communicateinfo> queryMineComm(Map<String, Object> maps) {
        return communicateDao.queryMineComm(maps);
    }

    @Override
    public String selectRoomNameByRoomId(int roomid) {
        return communicateDao.selectRoomNameByRoomId(roomid);
    }

    @Override
    public String selectUserNameByUserId(int userid) {
        return communicateDao.selectUserNameByUserId(userid);
    }

    @Override
    public int queryMineRows(int userid) {
        return communicateDao.queryMineRows(userid);
    }

    //添加会议申请
    @Override
    public boolean insertMessage(Communicateinfo communicateinfo) {
        return communicateDao.insertMessage(communicateinfo);
    }

    //修改会议申请
    @Override
    public boolean updateMessage(Communicateinfo communicateinfo) {
        return communicateDao.updateMessage(communicateinfo);
    }

    //查询审批列表
    @Override
    public List<Communicateinfo> queryAllComm(HashMap<String, Object> maps) {
        return communicateDao.queryAllComm(maps);
    }

    @Override
    public int queryAllRows() {
        return communicateDao.queryAllRows();
    }

    //修改会议审批的状态
    @Override
    public boolean updateCommunicateState(Integer comid, Integer state) {
        return communicateDao.updateCommunicateState(comid, state);
    }

    @Override
    public int selectRoomIsApply(Integer comid) {
        return communicateDao.selectRoomIsApply(comid);
    }

    @Override
    public boolean updateIsRoomApplyState(Integer comid, int isApply) {
        return communicateDao.updateIsRoomApplyState(comid, isApply);
    }

    @Transactional
    @Override
    public boolean updateCommStateAndRoomIsApply(Integer comid, Integer state) {
        try {
            updateCommunicateState(comid, state);
            int isApply = selectRoomIsApply(comid);
            if (isApply == 0) {
                updateIsRoomApplyState(comid, isApply + 1);
            } else {
                updateIsRoomApplyState(comid, isApply - 1);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
