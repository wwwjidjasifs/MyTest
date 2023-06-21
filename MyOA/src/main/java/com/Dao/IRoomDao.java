package com.Dao;

import com.Pojo.Roominfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IRoomDao {
    int queryAllRows();

    List<Roominfo> queryAllRoom(HashMap<String, Object> maps);

    boolean insertRoom(Roominfo roominfo);

    boolean updateRoom(Roominfo roominfo);

    boolean updateRoomState(@Param("roomid") Integer roomid, @Param("state") Integer state);

    boolean deleteRoom(int roomid);

    List<Roominfo> selectRoomByState();

    List<Roominfo> selectAll();
}
