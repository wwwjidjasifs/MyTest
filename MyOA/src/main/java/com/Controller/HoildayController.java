package com.Controller;

import com.Pojo.Holidayinfo;
import com.Pojo.UserInfo;
import com.Service.IHoildayService;
import com.Service.IUserInfoService;
import com.Utils.Layui;
import com.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/hoilday")
public class HoildayController {
    //注入服务层
    @Autowired
    private IHoildayService hoildayService;
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/applyLeave")
    public String applyLeave(){
        return "applyLeave";
    }
    @RequestMapping("/approveApply")
    public String approveApply(){
        return "approveApply";
    }
    //申请列表
    @ResponseBody
    @RequestMapping("user_LeaveHistory")
    public HashMap<String,Object> queryHolidayHistory(@RequestParam(name = "keyword",required = false,defaultValue = "") String type, Integer page, Integer limit,HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute("user");
        HashMap<String,Object> maps=new HashMap<>();
        maps.put("type",type);
        maps.put("userid",userInfo.getUserid());
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        List<Holidayinfo> holidayinfos=hoildayService.queryHolidayHistory(maps);
        int count=hoildayService.queryHistoryRows(userInfo.getUserid());
        session.setAttribute("holiday",holidayinfos);
        Layui data = Layui.data(count, holidayinfos);
        return data;
    }

    //添加请假申请
    @ResponseBody
    @RequestMapping("user_insertLeave")
    public Result insertApplyTable(@RequestBody Holidayinfo holidayinfo){
        boolean flag = hoildayService.insertApply(holidayinfo);
        if (flag){
            return new Result(200,"插入成功");
        }else{
            return new Result(100,"插入失败");
        }
    }

    //修改请假申请
    @ResponseBody
    @RequestMapping("user_updateTable")
    public Result updateTable(@RequestBody Holidayinfo holidayinfo){
        boolean flag = hoildayService.updateHoilday(holidayinfo);
        if (flag){
            return new Result(200,"修改成功");
        }else{
            return new Result(100,"修改失败");
        }
    }

    //审批列表
    @ResponseBody
    @RequestMapping("user_applyLeave")
    public HashMap<String,Object> queryAllHolidayInfo(@RequestParam(name = "keyword",required = false,defaultValue = "") String name, Integer page, Integer limit, HttpServletRequest request,HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute("user");
        String RoleName=userInfoService.selectRoleNameByRoleId(userInfo.getRoleid());
        HashMap<String,Object> maps=new HashMap<>();
        maps.put("name",name);
        maps.put("userid",userInfo.getUserid());
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        List<Holidayinfo> holidayinfos=null;
        int count=0;
        if (RoleName.equals("人事部经理")){
            maps.put("departmentid",userInfo.getDepartmentid());
            holidayinfos = hoildayService.querySecondandRen(maps);
            count = hoildayService.querySecondandRenRows(userInfo.getDepartmentid());
        }
        else if (RoleName.contains("经理")){

            maps.put("departmentid",userInfo.getDepartmentid());
            holidayinfos =hoildayService.queryAllHolidayInDept(maps);
            count= hoildayService.queryAllRowsInDept(userInfo.getDepartmentid());



        }else if(RoleName.contains("董事长")){
            holidayinfos = hoildayService.querySecond(maps);
            count = hoildayService.querySecondRows();
        }
        session.setAttribute("holiday",holidayinfos);
        Layui data = Layui.data(count, holidayinfos);
        return data;
    }
    //审批请假
    @ResponseBody
    @PostMapping("user_updateLeave")
    public Result updateApplyTable(int id,int state,int oldstate,HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute("user");
        String RoleName=userInfoService.selectRoleNameByRoleId(userInfo.getRoleid());
        if (RoleName.contains("经理")){
            //修改二级审核
            if (RoleName.equals("人事部经理")){
                //如果已经被别人审批过了人事部就进行二级审核
                if (oldstate==1){
                   Boolean b=hoildayService.updateIsApply(id,state);
                    if (b) {
                        return new Result(200, "审批成功");
                    } else {
                        return new Result(100, "审批失败");
                    }
                }
                //一级审核
                boolean b = hoildayService.updateApply(id, state);
                if (b) {
                    return new Result(200, "审批成功");
                } else {
                    return new Result(100, "审批失败");
                }
            }
            else {
                //一级审核
                boolean b = hoildayService.updateApply(id, state);
                if (b) {
                    return new Result(200, "审批成功");
                } else {
                    return new Result(100, "审批失败");
                }
            }
        }else if(RoleName.contains("董事长")){
            boolean b = hoildayService.updateIsApply(id,state);
            if (b){
                return new Result(200,"审批成功");
            }else{
                return new Result(100,"审批失败");
            }
        }
        return new Result();
    }
}
