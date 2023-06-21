package com.Controller;

import com.Pojo.Menuinfo;
import com.Pojo.Menus;
import com.Pojo.UserInfo;
import com.Service.IMenuService;
import com.Service.IUserInfoService;
import com.Utils.Layui;
import com.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IMenuService menuService;
    //显示首页
    @RequestMapping("admin")
    public String fromAdmin(){
        return "admin";
    }
    /**
     * 根据ID加载对应的菜单
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("menu_selectMenu")//用于登录后显示菜单
    public List<Menuinfo> selectMenu(HttpServletRequest request){
        //根据用户id查询出角色id再根据角色id查询出其相对应的菜单
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        Integer roleid = userInfoService.selectRole(userInfo.getUserid());
        //调用根据角色id查询出其相对应的菜单集合的方法
        List<Menuinfo> menuinfos = userInfoService.selectMenuByRole(roleid);
        return menuinfos;
    }
    //显示菜单页
    @RequestMapping("/menulist")
    public String MenuList(){
        return "menulist";
    }

    /**
     * 查询所有的菜单
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("menu_selectMenuList")
    public HashMap<String,Object> selectMenuList(Integer page, Integer limit){
        HashMap<String,Object> maps=new HashMap<>();
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        //调用查询菜单集合的方法
        List<Menuinfo> menuinfos = menuService.selectMenuAll(maps);
        //调用查询菜单总行数的方法
        int totalRows = menuService.selectTotalRows();
        //使用工具类装入map
        Layui data = Layui.data(totalRows, menuinfos);
        //返回数据
        return data;
    }
    /**
     * 添加菜单
     * @param menuinfo
     * @return
     */
    @ResponseBody
    @PostMapping("menu_insert")
    public Result insertMenu(@RequestBody Menuinfo menuinfo){
        boolean flag = menuService.insertMenu(menuinfo);
        if (flag){
            return new Result(200,"插入菜单成功");
        }else{
            return new Result(100,"插入菜单失败");
        }
    }
    /**
     * 修改菜单信息
     * @param menuinfo
     * @return
     */
    @ResponseBody
    @PostMapping("menu_UpdateMenu")
    public Result UpdateMenu(@RequestBody Menuinfo menuinfo){
        boolean b = menuService.UpdateMenu(menuinfo);
        if (b){
            return new Result(200,"修改菜单成功");
        }else{
            return new Result(100,"修改菜单失败");
        }
    }
    /**
     * 删除菜单
     * @param menuid
     * @return
     */
    @ResponseBody
    @PostMapping("menu_delete")
    public Result deleteMenu(String menuid){
        boolean flag = menuService.deleteMenu(menuid);
        if (flag){
            return new Result(200,"删除菜单成功");
        }else{
            return new Result(100,"删除菜单失败");
        }
    }
    /**
     * 修改菜单状态
     * @param state
     * @param menuid
     * @return
     */
    @RequestMapping("menu_modifyState")
    @ResponseBody
    public Result updateState(int state,String menuid){
        if(state==0){
            boolean flag = menuService.updateMenuState(state,menuid);
            if (flag)
            return new Result(150,"启用成功");
        }else{
            boolean flag = menuService.updateMenuState(state,menuid);
            if (flag)
            return new Result(50,"禁用成功");
        }
        return new Result(50,"修改失败");
    }

    /**
     * 查询出所有的菜单信息在树状组件显示
     * @return
     */
    //用于编辑角色菜单
    @ResponseBody
    @RequestMapping("menu_selects")
    public Object selects(Integer roleid){
        //roleid 3 财政部  menus所有父级菜单  menuids查询角色菜单表的数据得到菜单ids 第一个员工管理 菜单表查询pid等于员工管理id的
        //SELECT menuid,title,url FROM menuinfo where pid=#{menuid} 员工信息 设置为子集 获取子集员工信息   是否选中
        //查询所有父级菜单 有id title iconcls属性
        List<Menus> menus = menuService.selectFatherMenu();
        //根据角色id获取菜单集合 角色菜单表
        List<String> menuids = menuService.selectMenuIdListByRoleId(roleid);
        //根据父级id查询子级
        for (Menus menu : menus) {
            //根据父级菜单id查询出子集
            List<Menus> menus1 = menuService.selectMenusByMenuId(menu.getId());
            //设置子集
            menu.setChildren(menus1);
            //获取子级
            List<Menus> children = menu.getChildren();
            //循环子级
            for (Menus child : children) {
                //调用id判断方法并得到结果result
                boolean result= this.checkMenuId(child.getId(),menuids);
                //设置checked
                child.setChecked(result);
            }
        }
        return menus;
    }
    //是否选中菜单
    public boolean checkMenuId(String menuid,List<String> menuids){
        //循环menuids
        for (String s : menuids) {
            //判断是否相等并返回结果
            if(menuid.equals(s))
                return true;
        }
        return false;
    }

}
