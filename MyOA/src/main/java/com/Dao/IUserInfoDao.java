package com.Dao;

import com.Pojo.Menuinfo;
import com.Pojo.Roleinfo;
import com.Pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserInfoDao {
    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    UserInfo userLogin(UserInfo userInfo);
    //根据角色id查询相应的菜单
    List<Menuinfo> selectMenuByRole(Integer roleid);
    //根据用户id查询角色
    Integer selectRole(int userid);

    List<UserInfo> queryAllUser(Map<String, Object> maps);

    String selectDepNameByDepId(int departmentid);

    String selectRoleNameByRoleId(int roleid);

    int queryAllRows();

    int insertUser(UserInfo userInfo);
    //查询所有角色
    List<Roleinfo> selectRoleAll();
    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    int userUpdate(UserInfo userInfo);
    //更新用户状态
    int updateUserState(@Param("userid") int userid, @Param("state") int state);

    /**
     * 删除用户信息
     * @param userid
     * @return
     */
    int deleteUser(int userid);


    int updatePassword(@Param("userid") Integer userid, @Param("userpass") String userpass);

    //根据id修改用户头像
    int insertImage(@Param("userid")Integer userid,@Param("imageurl")String imageurl);

    /**
     * 通过userid获取用户对象
     * @param userid
     * @return
     */
    UserInfo getUser(int userid);

    /**
     * 批量添加用户数据 Excel表格导入
     * @param map
     * @return
     */
    int batchAddUser(HashMap<String,Object> map);
}
