package com.Controller;

import com.Pojo.Menus;
import com.Pojo.Roleinfo;
import com.Service.IMenuService;
import com.Service.IRoleService;
import com.Utils.Layui;
import com.Utils.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/rolelist")
    public String RoleList(){
        return "rolelist";
    }

    @ResponseBody
    @RequestMapping("/role_selectRole")
    public Map<String,Object> selectRoleAll(){
        HashMap<String,Object> maps=new HashMap<>();
        List<Roleinfo> roleinfos = roleService.selectRole(maps);
        maps.put("data",roleinfos);
        return maps;
    }

    @ResponseBody
    @RequestMapping("/role_selectRoleIdByUserId")
    public int selectRoleIdByUserId(Integer userid){
        Integer i = roleService.selectRoleIdByUserId(userid);
        if(i==null){
            return 0;
        }
        return i;
    }
    @ResponseBody
    @RequestMapping("/role_updateUserRole")
    public String updateUserRole(Integer userid,Integer roleid){
        return roleService.updateUserRole(userid,roleid)>0?"修改成功！":"修改失败！";
    }

    @ResponseBody
    @RequestMapping("role_selectRoleAlls")
    public Map<String,Object> selectRoleAll(Integer page, Integer limit){
        HashMap<String,Object> maps=new HashMap<>();
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        List<Roleinfo> roleinfos = roleService.selectRole(maps);
        Integer count = roleService.selectRoleRows();
        Layui data = Layui.data(count, roleinfos);
        return data;
    }

    @ResponseBody
    @RequestMapping("role_insertRole")
    public Result insertRole(@RequestBody Roleinfo roleinfo){
        boolean flag = roleService.insertRole(roleinfo);
        if (flag){
            return new Result(200,"插入角色成功");
        }else{
            return new Result(100,"插入角色失败");
        }
    }

    @ResponseBody
    @PostMapping( "role_update")
    public String updateRole(@RequestParam("roleid") Integer roleid, @RequestParam("rolename")String rolename , @RequestParam("menusjosn")String menusjosn){
//        //修改角色表
//        roleService.updateRole(rolename, roleid);//根据角色id修改角色名
//        //根据角色id删除角色菜单表的的菜单
//        menuService.deleteMenuByRoleid(roleid);
//        //创建集合
//        ArrayList<String> list=new ArrayList<>();
//        //将json字符串转换为json数组
//        JSONArray menuslist = JSON.parseArray(menusjosn);
//        //循环数组
//        for (int j = 0; j < menuslist.size(); j++) {
//            //获取父级菜单
//            JSONObject menu= (JSONObject)menuslist.get(j);
//            //添加父级id到集合
//            list.add(menu.getString("id"));
//            //获取子级children
//            JSONArray jsonArrays=JSONArray.parseArray(menu.getString("children"));
//            //循环子级menuarrays
//            for (int k = 0; k < jsonArrays.size(); k++) {
//                //获取子级对象
//                JSONObject menuchildren=(JSONObject)jsonArrays.get(k);
//                //将子级id添加到集合
//                list.add(menuchildren.getString("id"));
//            }
//            HashMap<String,Object> maps=new HashMap<>();
//            maps.put("roleid",roleid);
//            maps.put("menuidList",list);
//            //调用批量新增角色菜单的方法
//            menuService.batchInsertMenu(maps);
//        }

       Boolean flag=roleService.updateRoleandMenu(roleid,rolename,menusjosn);
        return  flag? "修改成功":"修改失败";
    }
    /**
     * 删除角色
     * @param roleid
     * @return
     */
    @ResponseBody
    @PostMapping("role_deleteRole")
    public Result deleteRole(int roleid){
        boolean flag = roleService.deleteRole(roleid);
        if (flag){
            return new Result(200,"删除角色成功");
        }else{
            return new Result(100,"删除角色失败");
        }
    }
}
