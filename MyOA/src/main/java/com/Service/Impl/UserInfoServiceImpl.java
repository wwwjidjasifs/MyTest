package com.Service.Impl;

import com.Dao.IMenuInfoDao;
import com.Dao.IUserInfoDao;
import com.Pojo.Menuinfo;
import com.Pojo.Roleinfo;
import com.Pojo.UserInfo;
import com.Service.IUserInfoService;
import com.Utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;
    @Override
    public UserInfo userLogin(UserInfo userInfo) {
        return userInfoDao.userLogin(userInfo);
    }
    //根据用户id查询角色
    @Override
    public Integer selectRole(int userid) {
        return userInfoDao.selectRole(userid);
    }
    //根据角色id查询相应的菜单
    @Override
    public List<Menuinfo> selectMenuByRole(Integer roleid) {
        return userInfoDao.selectMenuByRole(roleid);
    }
    //查询所有用户
    @Override
    public List<UserInfo> queryAllUser(Map<String, Object> maps) {
        return userInfoDao.queryAllUser(maps);
    }
    //根据部门id查询部门名字
    @Override
    public String selectDepNameByDepId(int departmentid) {
        return userInfoDao.selectDepNameByDepId(departmentid);
    }
    //根据角色id查询出角色名
    @Override
    public String selectRoleNameByRoleId(int roleid) {
        return userInfoDao.selectRoleNameByRoleId(roleid);
    }
    //查询所有用户总条数
    @Override
    public int queryAllRows() {
        return userInfoDao.queryAllRows();
    }
    //添加用户
    @Override
    public boolean insertUser(UserInfo userInfo) {
        int i = userInfoDao.insertUser(userInfo);
        return i > 0 ? true : false;
    }
    //查询所有角色
    @Override
    public List<Roleinfo> selectRoleAll() {
        return userInfoDao.selectRoleAll();
    }
    //修改用户
    @Override
    public boolean userUpdate(UserInfo userInfo) {
        int i = userInfoDao.userUpdate(userInfo);
        return i > 0 ? true : false;
    }
    //更新用户状态
    @Override
    public boolean updateUserState(Integer userid, Integer state) {
        int i = userInfoDao.updateUserState(userid, state);
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteUser(int userid) {
        int i = userInfoDao.deleteUser(userid);
        return i > 0 ? true : false;
    }

    @Override
    public boolean updatePassword(Integer userid, String newPass) {
        int i = userInfoDao.updatePassword(userid, newPass);
        return i>0?true:false;
    }

    /**
     * 根据id修改头像地址
     *
     * @param userid
     * @param imageurl
     * @return
     */
    public boolean updateImage(Integer userid, String imageurl) {
        int i = userInfoDao.insertImage(userid, imageurl);
        return i > 0 ? true : false;
    }

    @Transactional
    @Override
    public String UploadExcel(MultipartFile file) throws IOException{
        List<UserInfo> userInfos= ExcelUtil.importExcel(file);
        HashMap<String,Object> map=new HashMap<>();
//        for (Map.Entry<String,Object> entry:map.entrySet())
        map.put("userInfos",userInfos);
        try{
            userInfoDao.batchAddUser(map);
            return "导入成功！";
        }catch (Exception e){
            return "导入失败！";
        }

    }
}
