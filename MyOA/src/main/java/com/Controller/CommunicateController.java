package com.Controller;

import com.Pojo.Communicateinfo;
import com.Pojo.Roominfo;
import com.Pojo.UserInfo;
import com.Service.ICommunicateService;
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
@RequestMapping("/communicate")
public class CommunicateController {

    @Autowired
    public ICommunicateService communicateService;
    @Autowired
    private IRoomService roomService;

    //会议室申请视图
    @RequestMapping("/roomApply")
    private String roomApply(){
        return "roomApply";
    }
    //会议审批视图
    @RequestMapping("/roomApprove")
    private String roomApprove(){
        return "roomApprove";
    }
    //会议申请列表
    @ResponseBody
    @RequestMapping("communicate_selectMine")
    public HashMap<String,Object> queryAllMineCommunicateInfo(@RequestParam(name = "keyword",required = false,defaultValue = "") String name, Integer page, Integer limit, HttpSession session){
        HashMap<String,Object> maps=new HashMap<>();
        UserInfo userInfo=(UserInfo)session.getAttribute("user");
        maps.put("name",name);
        maps.put("userid",userInfo.getUserid());
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        List<Communicateinfo> communicateinfos = communicateService.queryMineComm(maps);
        //把用户id和会议id   转为name
        for (Communicateinfo communicateinfo:communicateinfos) {
            int roomid = communicateinfo.getRoomid();
            int userid = communicateinfo.getUserid();
            String roomname = communicateService.selectRoomNameByRoomId(roomid);
            String username = communicateService.selectUserNameByUserId(userid);
            communicateinfo.setRoomname(roomname);
            communicateinfo.setUsername(username);
        }
        //查询所有会议室，添加修改的时候 显示下拉框
        List<Roominfo> roominfos = roomService.selectRoomByState();
        //将所有的会议室信息存储到session中
        session.setAttribute("room",roominfos);
        int count = communicateService.queryMineRows(userInfo.getUserid());//查询当前用户的会议申请
        Layui data = Layui.data(count, communicateinfos);
        return data;
    }
    //添加
    @ResponseBody
    @RequestMapping("communicate_insert")
    public Result insertCommunicate(@RequestBody Communicateinfo communicateinfo){
        boolean b = communicateService.insertMessage(communicateinfo);
        if (b){
            return new Result(200,"插入成功！");
        }else{
            return new Result(100,"插入失败！");
        }
    }
    //修改
    @ResponseBody
    @RequestMapping("communicate_update")
    public Result updateCommunicate(@RequestBody Communicateinfo communicateinfo){
        boolean flag = communicateService.updateMessage(communicateinfo);
        if (flag){
            return new Result(200,"更新成功！");
        }else{
            return new Result(100,"更新失败！");
        }
    }

    //审批列表
    @ResponseBody
    @RequestMapping("communicate_selectAll")
    public HashMap<String,Object> queryAllCommunicateInfo(@RequestParam(name = "keyword",required = false,defaultValue = "") String name, Integer page, Integer limit, HttpSession session){
        HashMap<String,Object> maps=new HashMap<>();
        maps.put("name",name);
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        List<Communicateinfo> communicateinfos = communicateService.queryAllComm(maps);
        for (Communicateinfo communicateinfo:communicateinfos) {
            int roomid = communicateinfo.getRoomid();
            int userid = communicateinfo.getUserid();
            String roomname = communicateService.selectRoomNameByRoomId(roomid);
            String username = communicateService.selectUserNameByUserId(userid);
            communicateinfo.setRoomname(roomname);
            communicateinfo.setUsername(username);
        }
        int count = communicateService.queryAllRows();
        Layui data = Layui.data(count, communicateinfos);
        return data;
    }
    //点击审批状态
    @ResponseBody
    @PostMapping("communicate_updateState")
    public Result updateCommunicateState(Integer comid,Integer state){
       boolean flag=communicateService.updateCommStateAndRoomIsApply(comid,state);
        if (flag){
            return new Result(200,"修改状态成功！");
        }else{
            return new Result(100,"修改状态失败！");
        }
    }
    //查询可用的会议室显示到下拉框
    @ResponseBody
    @PostMapping("room_selectRoom")
    public Result selectRoomAll(){
        List<Roominfo> roominfos = roomService.selectRoomByState();
        if (roominfos!=null){
            return new Result(200,roominfos);
        }else{
            return new Result(100,"error");
        }
    }
}
