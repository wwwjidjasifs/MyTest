package com.Service.Impl;

import com.Dao.IMenuInfoDao;
import com.Pojo.Menuinfo;
import com.Pojo.Menus;
import com.Service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private IMenuInfoDao menuInfoDao;
    //查询所有菜单并且分页
    @Override
    public List<Menuinfo> selectMenuAll(HashMap<String,Object> maps) {
        return menuInfoDao.selectMenu(maps);
    }
    //查询所有菜单总条数
    @Override
    public int selectTotalRows() {
        return menuInfoDao.selectTotalRows();
    }

    @Override
    public boolean insertMenu(Menuinfo menuinfo) {
        int i = menuInfoDao.insertMenu(menuinfo);
        return i>0?true:false;
    }
    //修改菜单信息
    @Override
    public boolean UpdateMenu(Menuinfo menuinfo) {
        int i = menuInfoDao.UpdateMenu(menuinfo);
        return i>0?true:false;
    }

    @Override
    public boolean deleteMenu(String menuid) {
        int i = menuInfoDao.deleteMenu(menuid);
        return i>0?true:false;
    }
    //修改用户状态
    @Override
    public boolean updateMenuState(int state, String menuid) {
        int i = menuInfoDao.updateState(state,menuid);
        return i>0?true:false;
    }
    //查询菜单表pid为root的数据
    @Override
    public List<Menus> selectFatherMenu() {
        return menuInfoDao.selectFatherMenu();
    }

    //根据父级菜单id查询他的子集
    @Override
    public List<Menus> selectMenusByMenuId(String menuid) {
        return menuInfoDao.selectMenusByMenuId(menuid);
    }
    //根据角色id查询出他所拥有的权限
    @Override
    public List<String> selectMenuIdListByRoleId(int roleid) {
        return menuInfoDao.selectMenuIdListByRoleId(roleid);
    }
    //根据角色id删除掉角色菜单表的信息
    @Override
    public int deleteMenuByRoleid(Integer roleid) {
        return menuInfoDao.deleteMenuByRoleid(roleid);
    }
    //批量添加菜单信息
    @Override
    public int batchInsertMenu(HashMap<String, Object> idMaps) {
        return menuInfoDao.batchInsertMenu(idMaps);
    }
}
