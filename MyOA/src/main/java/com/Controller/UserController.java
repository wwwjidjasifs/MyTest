package com.Controller;

import com.Pojo.Departmentinfo;
import com.Pojo.Roleinfo;
import com.Pojo.UserInfo;
import com.Service.IDepartmentinfoService;
import com.Service.IUserInfoService;
import com.Utils.Layui;
import com.Utils.MD5Code;
import com.Utils.Result;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IDepartmentinfoService departmentinfoService;
    @Autowired
    private IUserInfoService userInfoService;
    //用户登录
    @ResponseBody
    @PostMapping("user_login")
    public Result login(UserInfo userInfo, HttpServletRequest request, HttpSession session){

        //调用MD5工具类实现密码加密
        userInfo.setUserpass(new MD5Code().getMD5ofStr(userInfo.getUserpass()));

        //用户登录 调用Service层的登录方法
        UserInfo userInfos = userInfoService.userLogin(userInfo);

        //此处判断用户是否被禁用
        if(null!=userInfos){
            //获取当前登录用的状态
            int state = userInfos.getState();
            //查询所有角色信息
            List<Roleinfo> roleinfos = userInfoService.selectRoleAll();
            session.setAttribute("role",roleinfos);
            //查询所有的部门
            List<Departmentinfo> departmentinfos = departmentinfoService.selectAll();
//            session.setAttribute("department",departmentinfos);
            for (Departmentinfo departmentinfo : departmentinfos) {
                if (departmentinfo.getDepartmentid()==userInfos.getDepartmentid()){
                    userInfos.setDepartmentname(departmentinfo.getDepartmentname());
                }
            }
            //将Service层登录返回的值设置在request作用域中
            request.getSession().setAttribute("user",userInfos);
            //判断当前用户的状态
            if (state==1){
                return new Result(200,"用户登录成功");
            } else{
                return new Result(404,"您已被禁用，请联系管理员！");
            }
        }else{
            return new Result(100,"登录失败");
        }
    }

    @RequestMapping("/userlistadmin")
    public String userlistadmin(){
        return "userlistadmin";
    }

    @RequestMapping("/userlist")
    private String userlist(){
        return "userlist";
    }
    /**
     * 查询所有用户信息 用于加载Layui数据表格 包含模糊搜索查询
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("/user_queryAll")
    public HashMap<String,Object> queryAllUserInfo(@RequestParam(name = "keyword",required = false,defaultValue = "") String name, Integer page, Integer limit){
        HashMap<String,Object> maps=new HashMap<>();
        maps.put("name",name);
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);
            maps.put("limit",limit);
        }
        //查询所有用户
        List<UserInfo> userInfos = userInfoService.queryAllUser(maps);
        for (UserInfo userInfo:userInfos) {
            int departmentid = userInfo.getDepartmentid();//获取用户的部门id
            int roleid = userInfo.getRoleid();//获取角色id
            String departmentname = userInfoService.selectDepNameByDepId(departmentid);
            String rolename = userInfoService.selectRoleNameByRoleId(roleid);
            userInfo.setDepartmentname(departmentname);
            userInfo.setRolename(rolename);
        }
        int count = userInfoService.queryAllRows();
        Layui data = Layui.data(count, userInfos);
        return data;
    }
    /**
     * 用户数据添加
     * @param userInfo
     * @return
     */
    @ResponseBody
    @PostMapping("/user_insert")
    public Result insertUserinfo(@RequestBody UserInfo userInfo){
        userInfo.setUserpass(new MD5Code().getMD5ofStr(userInfo.getUserpass()));
        boolean flag = userInfoService.insertUser(userInfo);
        if (flag){
            return new Result(200,"插入成功！");
        }else{
            return new Result(100,"插入失败！");
        }
    }


    /**
     * 修改/更新用户信息
     * @param userInfo
     * @return
     */
    @ResponseBody
    @PostMapping("/update_user")
    public Result updateUser(@RequestBody UserInfo userInfo){
        userInfo.setUserpass(new MD5Code().getMD5ofStr(userInfo.getUserpass()));
        boolean flag = userInfoService.userUpdate(userInfo);
        if (flag){
            return new Result(200,"修改成功！");
        }else{
            return new Result(100,"修改失败！");
        }
    }


    /**
     * 更新用户状态
     * @param userid
     * @param state
     * @return
     */
    @ResponseBody
    @PostMapping("user_updateState")
    public Result updateUserState(Integer userid,Integer state){
        boolean flag = userInfoService.updateUserState(userid, state);
        if (flag){
            return new Result(200,"修改成功！");
        }else{
            return new Result(100,"修改失败！");
        }
    }
    /**
     * 删除用户
     * @param userid
     * @return
     */
    @ResponseBody
    @PostMapping("delete_user")
    public Result deleteUser(int userid){
        boolean flag = userInfoService.deleteUser(userid);
        if (flag){
            return new Result(200,"删除成功！");
        }else{
            return new Result(100,"删除失败！");
        }
    }
    /**
     * 用户注销
     * @param session
     * @return
     */
    @RequestMapping("user_logout")
    public String logout(HttpSession session){
        session.invalidate();//销毁
        return "redirect:/index.jsp";
    }
    //修改密码
    @RequestMapping("user_updatePass")
    @ResponseBody
    public Result updatePass(Integer userid,String newPass){
        boolean b = userInfoService.updatePassword(userid,new MD5Code().getMD5ofStr(newPass));
        if (b) {
            return new Result(200,"修改密码成功");
        } else {
            return new Result(100,"修改密码失败");
        }
    }

    /**
     * 上传用户头像
     * @param headpic  文件名
     * @return
     * @throws IOException
     */
    @PostMapping(value = "user_uploadImg")
    @ResponseBody
    public Result uploadImage(@RequestParam(value = "file", required = false) MultipartFile headpic, HttpSession session) throws IOException {
        if (headpic !=null && !headpic.isEmpty()){
            //获取上传文件的原始文件名
            String filename = headpic.getOriginalFilename();
            //将上传的文件保存到指定位置的代码
            headpic.transferTo(new File("D:\\Information\\javaweb\\MyOA\\MyOA\\src\\main\\webapp\\layui\\upload\\"+filename));
           HashMap<String,String> map = new HashMap<>();
           map.put("src","/MyOA/layui/upload/"+filename);
            UserInfo user = (UserInfo) session.getAttribute("user");
            user.setImageurl("/MyOA/layui/upload/" + filename);
            session.setAttribute("user",user);
            Result result = new Result();
            result.setCode(0);
            result.setData(map);
            return result;
        }
        return new Result(1,"文件为空");
    }
    //修改头像
    @RequestMapping("user_updateImg")
    @ResponseBody
    public Result updateImg(String imageurl,Integer userid){
        boolean flag = userInfoService.updateImage(userid, imageurl);
        if(flag){
            return new Result(0);

        }
        return new Result(100);
    }
    //excel数据导入
    @PostMapping("upload_file")
    @ResponseBody
    public Result uploadfile(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException, InvalidFormatException {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartRequest.getFile("file");
        if (file.isEmpty()) {
            return new Result(150,"文件不存在");
        }
        String msg = userInfoService.UploadExcel(file);
        if (msg.equals("导入成功！")) {
            return new Result(200,msg);
        } else {
            return new Result(100,msg);
        }
    }
}
