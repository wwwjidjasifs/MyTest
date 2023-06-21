package com.Service.Impl;

import com.Dao.IMenuInfoDao;
import com.Dao.IRoleDao;
import com.Pojo.Roleinfo;
import com.Service.IRoleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IMenuInfoDao menuInfoDao;
    @Override
    public List<Roleinfo> selectRole(HashMap<String,Object> maps) {
        return roleDao.selectRole(maps);
    }

    @Override
    public Integer selectRoleIdByUserId(Integer userid) {
        return roleDao.selectRoleIdByUserId(userid);
    }
    //修改角色，根据用户id
    @Override
    public Integer updateUserRole(Integer userid, Integer roleid) {

            Integer result=0;
            //判断用户有没有角色信息 没有则新增
            if(this.selectRoleIdByUserId(userid)==null){
                result = roleDao.insertUserRole(userid,roleid);
            }else{
                result = roleDao.updateUserRole(userid,roleid);
            }
            return result;

    }

    @Override
    public Integer selectRoleRows() {
        return roleDao.selectRoleRows();
    }

    @Override
    public boolean insertRole(Roleinfo roleinfo) {
        Integer i = roleDao.insertRole(roleinfo);
        return i>0?true:false;
    }

    @Override
    public int updateRole(String rolename, Integer roleid) {
        return roleDao.updateRole(rolename,roleid);
    }
    //修改角色表和角色菜单表
    @Transactional
    @Override
    public Boolean updateRoleandMenu(Integer roleid, String rolename, String menusjosn) {
        try {
            //修改角色表
            updateRole(rolename, roleid);
            //根据角色id删除角色菜单表的的菜单
            menuInfoDao.deleteMenuByRoleid(roleid);

            //将json字符串转换为json数组
            JSONArray menuslist = JSON.parseArray(menusjosn);
            //循环数组
            for (int j = 0; j < menuslist.size(); j++) {
                //创建集合
                ArrayList<String> list = new ArrayList<>();
                //获取父级菜单
                JSONObject menu = (JSONObject) menuslist.get(j);
                //添加父级id到集合
                list.add(menu.getString("id"));
                //获取子级children
                JSONArray jsonArrays = JSONArray.parseArray(menu.getString("children"));
                //循环子级menuarrays
                for (int k  =0; k < jsonArrays.size(); k++) {
                    //获取子级对象
                    JSONObject menuchildren = (JSONObject) jsonArrays.get(k);
                    //将子级id添加到集合
                    list.add(menuchildren.getString("id"));
                }
                HashMap<String, Object> maps = new HashMap<>();
                maps.put("roleid", roleid);
                maps.put("menuidList", list);
                //调用批量新增角色菜单的方法
                menuInfoDao.batchInsertMenu(maps);
            }
            return true;
        }
        catch(Exception e){
                return false;
        }
    }
    @Transactional
    @Override
    public boolean deleteRole(int roleid) {
        Integer i = roleDao.deleteRole(roleid);
        Integer num=roleDao.deleteMenuRole(roleid);
        if (i>0&&num>0){
            return true;
        }
        return false;
    }


}
