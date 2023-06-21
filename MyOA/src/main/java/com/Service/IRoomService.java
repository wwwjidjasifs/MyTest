package com.Service;

import com.Pojo.Roominfo;

import java.util.HashMap;
import java.util.List;

public interface IRoomService {
    //    查询所有会议室数据
    List<Roominfo> queryAllRoom(HashMap<String, Object> maps);
//    查询会议室总条数

    int queryAllRows();

    //添加会议室信息
    boolean insertRoom(Roominfo roominfo);

    boolean updateRoom(Roominfo roominfo);

    boolean updateRoomState(Integer roomid, Integer state);

    boolean deleteRoom(int roomid);

    //<!--    查询没有被申请和状态开启的会议室-->
    List<Roominfo> selectRoomByState();
}
