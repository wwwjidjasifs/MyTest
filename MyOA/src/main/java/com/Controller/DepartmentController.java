package com.Controller;

import com.Pojo.Departmentinfo;
import com.Service.IDepartmentinfoService;
import com.Utils.Layui;
import com.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentinfoService departmentinfoService;
    @RequestMapping("/departmentlist")
    //打开部门表格页面
    public String departmentlist(){
        return "department";
    }

    @ResponseBody
    @RequestMapping("department_selectAll")
    public HashMap<String,Object> queryAllDeptInfo(@RequestParam(name = "keyword",required = false,defaultValue = "") String name, Integer page, Integer limit, HttpSession session){
        HashMap<String,Object> maps=new HashMap<>();
        maps.put("name",name);
        if(page!=null && limit!=null){
            maps.put("page",(page-1)*limit);//分页
            maps.put("limit",limit);
        }
        List<Departmentinfo> departmentinfoList = departmentinfoService.queryAllDep(maps);
        session.setAttribute("department",departmentinfoList);
        int count = departmentinfoService.queryAllRows();
        Layui data = Layui.data(count, departmentinfoList);
        return data;
    }
    @ResponseBody
    @RequestMapping("department_insert")
    public Result insert(@RequestBody Departmentinfo departmentinfo){
        boolean flag = departmentinfoService.insertDept(departmentinfo);
        if(flag){
            return new Result(200,"添加成功");
        }else{
            return new Result(100,"添加失败");
        }
    }

    @ResponseBody
    @RequestMapping("department_update")
    public Result update(@RequestBody Departmentinfo departmentinfo){
        boolean b = departmentinfoService.updateDept(departmentinfo);
        if(b){
            return new Result(200,"修改成功");
        }else{
            return new Result(100,"修改失败");
        }
    }
    @ResponseBody
    @RequestMapping("department_delete")
    public Result delete(int departmentid){
        boolean flag = departmentinfoService.deleteDept(departmentid);
        if(flag){
            return new Result(200,"删除成功");
        }else{
            return new Result(100,"删除失败");
        }
    }
}
