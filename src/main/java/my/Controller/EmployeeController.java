package my.Controller;

import my.Bean.Employee;
import my.Service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import my.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//控制器是springMVC的核心内容：获取请求参数；处理业务逻辑；绑定模型和视图
/*
@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值
 */
@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //删除
    @RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(@PathVariable("empId") Integer empId) {
        int res = 0;
        if (empId > 0) {
            res = employeeService.deleteEmpById(empId);
        }
        if (res != 1) {
            return JsonMsg.fail().addInfo("emp_del_error", "员工删除异常");
        }
        return JsonMsg.success();

    }

    //更改
    @RequestMapping(value = "/updateEmp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateEmp(@PathVariable("empId") Integer empId, Employee employee) {
        int res = employeeService.updateEmpById(empId, employee);
        if (res != 1) {
            return JsonMsg.fail().addInfo("em_update_error", "更新异常");
        }
        return JsonMsg.success();
    }

    //查询
    @RequestMapping(value = "/checkEmpExists", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExists(@Param("empName") String empName) {
        //对输入的姓名与邮箱格式进行验证
        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regName)) {
            return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Employee employee = employeeService.getEmpByName(empName);
        if (employee != null) {
            return JsonMsg.fail().addInfo("name_reg_error", "用户名重复");
        } else {
            return JsonMsg.success();
        }
    }

    //新增记录后，查询最新页数
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages() {
        int limit=5;
        int totalItems = employeeService.getEmpCount();
        //获取总页数
        int temp = totalItems / 5;
        int totalPages = (totalItems % 5 == 0) ? temp : temp + 1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

      //新增员工
    @RequestMapping(value = "/addEmp", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg addEmp(Employee employee){
        int res=employeeService.addEmp(employee);
        if(res ==1){
            return JsonMsg.success();
        }else{
            return JsonMsg.fail();
        }
    }

    //根据Id号查询员工信息
    @RequestMapping(value = "/getEmpById/{empId}", method=RequestMethod.GET)
    @ResponseBody
    public JsonMsg getEmpById(@PathVariable("empId") Integer empId){
        Employee employee = employeeService.getEmpById(empId);
        if(employee != null){
            return JsonMsg.success().addInfo("employee", employee);
        }else{
            return JsonMsg.fail();
        }
    }
    //查询指定页码包含的数据
    @RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
    public ModelAndView getEmp(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("employeePage");
        int limit =5;
        /*
        记录的偏移量，从第offset行记录开始查询
         */
        int offset=(pageNo-1)*limit;
        //获取指定页数包含的员工信息
       List<Employee> employees = employeeService.getEmpList(offset, limit);
       //获取总的记录数
        int totalItems =employeeService.getEmpCount();
        //获取总页数
        int temp=totalItems/limit;
        int totalPages = (totalItems%limit ==0)? temp:temp+1;
        //当前页数
        int curPage = pageNo;
        //将上述查询结果放到Model中，在JSP页面中进行展示
        mv.addObject("employees", employees)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;


    }



}
