package com.Dao;

import com.Pojo.Roleinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IRoleDao {
    List<Roleinfo> selectRole(HashMap<String,Object> maps);
    //根据用户id查询角色
    Integer selectRoleIdByUserId(Integer userid);

    /**
     * 新增用户角色表
     * @param userid
     * @param roleid
     * @return
     */
    Integer insertUserRole(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    /**
     * 操作用户角色表 根据用户id修改角色id
     * @param userid
     * @param roleid
     * @return
     */
    Integer updateUserRole(@Param("userid")Integer userid,@Param("roleid")Integer roleid);

    Integer selectRoleRows();

    Integer insertRole(Roleinfo roleinfo);

    int updateRole(@Param("rolename") String rolename, @Param("roleid") Integer roleid);

    Integer deleteRole(int roleid);
    Integer deleteMenuRole(int roleid);
}
