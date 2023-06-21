package com.Service;

import com.Pojo.Roleinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface IRoleService {
    List<Roleinfo> selectRole(HashMap<String, Object> maps);

    //根据用户id查询当前角色
    Integer selectRoleIdByUserId(Integer userid);

    //根据用户id修改他的角色
    Integer updateUserRole(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    Integer selectRoleRows();

    boolean insertRole(Roleinfo roleinfo);

    int updateRole(@Param("rolename") String rolename,@Param("roleid") Integer roleid);
    //修改角色表和角色菜单表
    Boolean updateRoleandMenu(Integer roleid,String rolename , String menusjosn);

    boolean deleteRole(int roleid);
}
