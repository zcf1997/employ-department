package my.Test;

import my.Bean.Department;
import my.Mapper.DepartmentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:dispatcher-servlet.xml"})
public class TestDepartmentMapper {
    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void insertOneTest(){
        Department department = new Department(7,"开发部","小章");
        //举例子时需要注意，外键deptId要根据tbl_emp中存在的举例子，不能随便举例
        int res1 = departmentMapper.insertOne(department);
        System.out.println(res1);
    }
    @Test
    public void deleteOneByDept_idTest(){
        int res2 = departmentMapper.deleteOneByDept_id(1);
        System.out.println(res2);
    }
    @Test
    public void updateDeptTest(){
        Department department= new Department(3,"开发部","老李");
        int res3 = departmentMapper.updateOneByDept_id(department);
        System.out.println(res3);
    }
    @Test
    public void selectOneByDept_idTest(){
        Department res4 = departmentMapper.selectOneByDept_id(2);
        System.out.println(res4);
    }
    @Test
    public void selectOneByDept_nameTest(){
        Department res5=departmentMapper.selectOneByDept_name("研发部");
        System.out.println(res5);
    }
    @Test
    public void selectOneByDept_leaderTest(){
        Department res6=departmentMapper.selectOneByDept_leader("小侯");
        System.out.println(res6);
    }
    @Test
    public void selectDeptListTest(){
        List<Department> res7=departmentMapper.selectDeptList();
        for(int i=0; i< res7.size();i++){
            System.out.println(res7.get(i));
        }
    }
    @Test
    public void selectDeptByLimitAndOffsetTest(){
       List<Department> departments = departmentMapper.selectDeptByLimitAndOffset(0,5);
        System.out.println(departments.size());
        for (int i = 0; i < departments.size(); i++) {
            System.out.println(departments.get(i));
        }
    }
    @Test
    public void countDept(){
        int count = departmentMapper.countDepts();
        System.out.println(count);
    }
}
