package my.Test;

import my.Bean.Employee;
import my.Mapper.EmployeeMapper;
import org.junit.Test;//如果不是这个test包，会报错
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:dispatcher-servlet.xml"})

public class TestEmployeeMapper {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    //单条记录插入
    @Test
    public void insertOneTest() {
        Employee employee = new Employee(10,"小章","123@qq.com","男",1);
        int res = employeeMapper.insertOne(employee);
        System.out.println(res);
    }

    @Test
    public void insertEmpBatchTest(){
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 1; i < 500; i++) {
            //UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法
            String uid = UUID.randomUUID().toString().substring(0, 10);
            Employee employees = new Employee(i,"name_"+uid,uid+"@qq.com",i%2==0? "F":"M",i%6+1);
            employeeMapper.insertOne(employees);
        }
    }
    @Test
    public void updateOneByIdTest(){
        Employee employee2 =
                new Employee(6, "aa", "aa@qq.com", "女", 3);
        //传的是一个employee，因此首先需要先new一个对象，然后把这个对象传进去
        int res2= employeeMapper.updateOneById(employee2);
        System.out.println(res2);
    }
    @Test
    public void selectOneByIdTest(){
        Employee res3= employeeMapper.selectOneByEmp_id(1);
        System.out.println(res3);
    }

    @Test
    public void selectOneByNameTest(){
        Employee res4 = employeeMapper.selectOneByEmp_name("老张");
        System.out.println(res4);
    }

    @Test
    public void selectWithDeptByIdTest(){
        Employee res5 = employeeMapper.selectWithDeptById(7);
        System.out.println(res5);
    }

    @Test
    public void countEmpsTest(){

        System.out.println(employeeMapper.countEmps());
    }

    @Test
    public void selectByLimitAndOffsetTest(){
        List<Employee> list = employeeMapper.selectByLimitAndOffset(5, 10);
        System.out.println(list.size());
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
    @Test
    public void deleteOneByIdTest(){
        int res4 = employeeMapper.deleteOneByEmp_id(1);
        System.out.println(res4);
    }
}
