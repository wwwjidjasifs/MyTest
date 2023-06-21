package com.Service;

import com.Pojo.Menuinfo;
import com.Pojo.Roleinfo;
import com.Pojo.UserInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IUserInfoService {
    UserInfo userLogin(UserInfo userInfo);
    //根据用户id查询角色
    Integer selectRole(int userid);
    //根据角色id查询相应的菜单
    List<Menuinfo> selectMenuByRole(Integer roleid);

     List<UserInfo> queryAllUser(Map<String, Object> maps);
    //根据部门id查询部门名字
    String selectDepNameByDepId(int departmentid);
    //根据角色id查询出角色名
    String selectRoleNameByRoleId(int roleid);

    int queryAllRows();

    boolean insertUser(UserInfo userInfo);
    //查询所有角色
    List<Roleinfo> selectRoleAll();
    //修改用户
    boolean userUpdate(UserInfo userInfo);

    boolean updateUserState(Integer userid, Integer state);
    //删除用户
    boolean deleteUser(int userid);

    boolean updatePassword(Integer userid, String md5ofStr);
    /**
     * 根据id修改头像地址
     *
     * @param userid
     * @param imageurl
     * @return
     */
    boolean updateImage(Integer userid, String imageurl);
    /**
     * 使用excel表格导入数据
     * @param file
     * @return
     */
    String UploadExcel(MultipartFile file) throws IOException, InvalidFormatException;
}
