package com.Service.Impl;

import com.Dao.IRoomDao;
import com.Pojo.Roominfo;
import com.Service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private IRoomDao roomDao;

    @Override
    public List<Roominfo> queryAllRoom(HashMap<String, Object> maps) {
        return roomDao.queryAllRoom(maps);
    }

    @Override
    public int queryAllRows() {
        return roomDao.queryAllRows();
    }

    @Override
    public boolean insertRoom(Roominfo roominfo) {
        return roomDao.insertRoom(roominfo);
    }

    @Override
    public boolean updateRoom(Roominfo roominfo) {
        return roomDao.updateRoom(roominfo);
    }

    @Override
    public boolean updateRoomState(Integer roomid, Integer state) {
        return roomDao.updateRoomState(roomid,state);
    }

    @Override
    public boolean deleteRoom(int roomid) {
        return roomDao.deleteRoom(roomid);
    }
    //查询出所有会议室（状态为启用）
    @Override
    public List<Roominfo> selectRoomByState() {
        return roomDao.selectRoomByState();
    }


}
