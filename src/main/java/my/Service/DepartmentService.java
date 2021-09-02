package my.Service;

import my.Bean.Department;
import my.Mapper.DepartmentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public int addDept(Department department){
        return departmentMapper.insertOne(department);
    }

    public int updateDeptById(@Param("deptId") Integer deptId, @Param("department") Department department){
        return departmentMapper.updateOneByDept_id(department);
    }
    public int deleteDeptById(Integer deptId){
        return departmentMapper.deleteOneByDept_id(deptId);
    }
    public Department getDeptById(Integer deptId){
        return departmentMapper.selectOneByDept_id(deptId);
    }

    public int getDeptCount(){
        return departmentMapper.countDepts();
    }
    public List<Department> getDeptList(Integer offset, Integer limit){
        return departmentMapper.selectDeptByLimitAndOffset(offset, limit);
    }
    public List<Department> getDeptName(){
        return departmentMapper.selectDeptList();
    }

}
