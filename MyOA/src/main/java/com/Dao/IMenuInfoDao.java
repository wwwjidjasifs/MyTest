package com.Dao;

import com.Pojo.Menuinfo;
import com.Pojo.Menus;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IMenuInfoDao {
    //查询所有菜单并且分页
    List<Menuinfo> selectMenu(HashMap<String, Object> maps);

    //查询所有菜单总条数
    int selectTotalRows();
    //插入菜单
    int insertMenu(Menuinfo menuinfo);

    /**
     * 修改菜单信息
     * @param menuinfo
     * @return
     */
    int UpdateMenu(Menuinfo menuinfo);

    int deleteMenu(String menuid);
    //修改菜单状态
    int updateState(@Param("state") int state, @Param("menuid") String menuid);
    //查询菜单表pid为root的数据
    List<Menus> selectFatherMenu();
    //根据父级菜单id查询他的子集
    List<Menus> selectMenusByMenuId(String menuid);
    //根据角色id查询出他所拥有的权限
    List<String> selectMenuIdListByRoleId(int roleid);

    int deleteMenuByRoleid(Integer roleid);
    //批量添加菜单信息
    int batchInsertMenu(HashMap<String, Object> idMaps);
}
