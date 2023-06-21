package com.Service;

import com.Pojo.Menuinfo;
import com.Pojo.Menus;

import java.util.HashMap;
import java.util.List;

public interface IMenuService {
    List<Menuinfo> selectMenuAll(HashMap<String,Object> maps);
    int selectTotalRows();

    boolean insertMenu(Menuinfo menuinfo);

    boolean UpdateMenu(Menuinfo menuinfo);

    boolean deleteMenu(String menuid);
    //修改用户状态
    boolean updateMenuState(int state, String menuid);
    List<Menus> selectFatherMenu();
    List<String> selectMenuIdListByRoleId(int roleid);
    List<Menus> selectMenusByMenuId(String menuid);

    int deleteMenuByRoleid(Integer roleid);

    int batchInsertMenu(HashMap<String, Object> idMaps);

}
