package com.Controller;

import com.Pojo.Roominfo;
import com.Service.IRoomService;
import com.Utils.Layui;
import com.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    //依赖注入
    @Autowired
    private IRoomService roomService;
    @RequestMapping("/roomList")
    private String roomList(){
        return "roomList";
    }

    @ResponseBody
    @RequestMapping("room_queryAll")
    public HashMap<String, Object> queryAllRoomInfo(@RequestParam(name = "keyword", required = false, defaultValue = "") String roomname, Integer page, Integer limit) {
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("roomname", roomname);
        if (page != null && limit != null) {
            maps.put("page", (page - 1) * limit);
            maps.put("limit", limit);
        }
        List<Roominfo> roominfos = roomService.queryAllRoom(maps);
        int count = roomService.queryAllRows();
        Layui data = Layui.data(count, roominfos);
        return data;
    }
    //添加
    @ResponseBody
    @RequestMapping("room_insert")
    public Result insertRoom(@RequestBody Roominfo roominfo){
        boolean b = roomService.insertRoom(roominfo);
        if (b){
            return new Result(200,"插入成功！");
        }else{
            return new Result(100,"插入失败！");
        }
    }
    //修改
    @ResponseBody
    @RequestMapping("room_update")
    public Result updateRoom(@RequestBody Roominfo roominfo){
        boolean flag = roomService.updateRoom(roominfo);
        if (flag){
            return new Result(200,"修改成功！");
        }else{
            return new Result(100,"修改失败！");
        }
    }
    //会议室状态修改
    @ResponseBody
    @RequestMapping("room_updateRoomState")
    public Result updateRoomState(Integer roomid, Integer state, HttpSession session){
        boolean flag = roomService.updateRoomState(roomid,state);
        if (flag){
            return new Result(200,"修改状态成功！");
        }else{
            return new Result(100,"修改状态失败！");
        }
    }
    @ResponseBody
    @PostMapping("room_delete")
    public Result deleteRoom(int roomid){
        boolean flag = roomService.deleteRoom(roomid);
        if (flag){
            return new Result(200,"删除成功！");
        }else{
            return new Result(100,"删除失败！");
        }
    }
}
