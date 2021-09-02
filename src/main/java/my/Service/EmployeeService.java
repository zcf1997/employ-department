package my.Service;

import my.Bean.Employee;
import my.Mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


    public int getEmpCount(){
        return  employeeMapper.countEmps();
    }
    public List<Employee> getEmpList(Integer offset, Integer limit){
        return employeeMapper.selectByLimitAndOffset(offset, limit);
    }
    public Employee getEmpById(Integer empId){
        return employeeMapper.selectOneByEmp_id(empId);
    }
    public Employee getEmpByName(String empName){

        return employeeMapper.selectOneByEmp_name(empName);
    }
    public int updateEmpById(Integer empId, Employee employee){
        //注意，传多个参数的时候一定记得加param注解
        return employeeMapper.updateOneById(employee);
    }
    public int deleteEmpById(Integer empId){

        return employeeMapper.deleteOneByEmp_id(empId);
    }
    public int addEmp(Employee employee){

        return employeeMapper.insertOne(employee);
    }
}
