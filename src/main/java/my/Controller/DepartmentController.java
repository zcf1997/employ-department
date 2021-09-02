package my.Controller;

import my.Bean.Department;
import my.Bean.Employee;
import my.Service.DepartmentService;
import my.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
@RequestMapping(value = "/dept")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    //删除
    @RequestMapping(value = "/delDept/{deptId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteDept(@PathVariable("deptId") Integer deptId) {
        int res = 0;
        if (deptId > 0) {
            res = departmentService.deleteDeptById(deptId);
        }
        if (res != 1) {
            return JsonMsg.fail().addInfo("del_dept_error", "删除异常");
        }
        return JsonMsg.success();
    }

    //更新
    @RequestMapping(value = "/updateDept/{deptId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateDeptById(@PathVariable("deptId") Integer deptId, Department department) {
        //@PathVariable注解声明了值(“id”) 取的是{id}的值，和形参取什么名字无关（但强烈推荐参数取名保持一致！）
        //@Param注解是用来声明dao层方法参数名，和mybatis的xml配置文件里面字段名相对应
        int res = 0;
        if (deptId > 0) {
            res = departmentService.updateDeptById(deptId,department);
        }
        if (res != 1) {
            return JsonMsg.fail().addInfo("update_dept_error", "部门更新失败");
        }
        return JsonMsg.success();
    }

    //新增
    @RequestMapping(value = "/addDept/{deptId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg addDeptById(Department department) {
        int res = departmentService.addDept(department);
        if (res != 1) {
            return JsonMsg.fail().addInfo("add_dept_error", "部门添加异常");
        }
        return JsonMsg.success();
    }

    //查询部门信息总页码数
    @RequestMapping(value = "/getTotalPage", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage() {
        int limit = 5;
        int totalItems = departmentService.getDeptCount();
        //获取总页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % 5 == 0) ? temp : temp + 1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    //查询部门信息
    @RequestMapping(value = "/getDeptId/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptId(@PathVariable("deptId") Integer deptId) {
        Department department = null;
        if (deptId > 0) {
            department = departmentService.getDeptById(deptId);
        }
        if (department != null) {
            return JsonMsg.success().addInfo("department", department);
        }
        return JsonMsg.fail().addInfo("get_dept_error", "无部门信息");
    }

    //分页查询：返回指定页数对应的数据
    @RequestMapping(value = "/getDeptList", method = RequestMethod.GET)
    public ModelAndView getDeptList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mv = new ModelAndView("departmentPage");
        int limit = 5;
        /*
        记录的偏移量，从第offset行记录开始查询
         */
        int offset = (pageNo - 1) * limit;
        //获取指定页数包含的员工信息
        List<Department> departments = departmentService.getDeptList(offset, limit);
        //获取总的记录数
        int totalItems = departmentService.getDeptCount();
        //获取总页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp + 1;
        //当前页数
        int curPage = pageNo;
        //将上述查询结果放到Model中，在JSP页面中进行展示
        mv.addObject("departments", departments)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }

    //查看部门
    @RequestMapping(value = "/getDeptName", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptName() {
            List<Department> departmentList = departmentService.getDeptName();

        if (departmentList != null) {
            return JsonMsg.success().addInfo("departmentList", departmentList);
        }
        return JsonMsg.fail();
    }
}



