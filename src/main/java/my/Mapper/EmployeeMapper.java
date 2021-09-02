package my.Mapper;

import my.Bean.Employee;
import org.apache.ibatis.annotations.*;
import java.util.List;


public interface EmployeeMapper {
    /*这个接口里描述的是对数据库操作的一些方法
    采用XML方式构建映射器，这个映射器包含一个接口和一个XML，接口包括增、删
    、改、查方法，XML中将这些方法映射到相应对的Bean对象上
     */
    //接口采用注解的方式

//emp_id, emp_name, emp_email, gender,department_id一直被重复使用，定义为ISNERT_FIELDS
    String SELECT_FIELDS = "emp_id empId, emp_name empName, emp_email empEmail, gender,department_id departmentId";

    //insert的语法格式相似sql语法格式：{“insert into 表名（字段1，字段2....）”， “values（值1，值2....）”}

    @Insert({"INSERT INTO tbl_emp (emp_name,emp_email, gender, department_id) VALUES(#{empName}, #{empEmail}, #{gender}, #{departmentId})"})
    int insertOne( Employee employee);

    //update语法跟sql相似：{“update 表名”，  “set 字段名1=值1，字段名2=值2...”， “ where 条件”}
    //回车键自动加+号
    @Update({"UPDATE tbl_emp SET emp_name =#{empName},emp_email =#{empEmail}, gender=#{gender}, department_id =#{departmentId} WHERE emp_id =#{empId}" })


    int updateOneById(Employee employee);
//Mapper中声明的方法，在绑定的映射文件中必须能找到对应的sql标签，且方法名与对应的sql标签的id值必须相同。
    @Delete({"DELETE FROM tbl_emp WHERE emp_id=#{empId}"})
    int deleteOneByEmp_id( Integer empId);

    //根据emp_id选择，根据department_id选择
    @Select({"SELECT emp_id FROM tbl_emp WHERE emp_id=#{empId}"})
    Employee selectOneByEmp_id( Integer empId);

    @Select({"SELECT emp_name FROM tbl_emp WHERE emp_name=#{empName}"})
    Employee selectOneByEmp_name(String empName);

   @Select({"SELECT emp_id, emp_name, emp_email, gender FROM tbl_emp WHERE department_id=#{departmentId}"})
    Employee selectWithDeptById(Integer empId);//单个参数不需要加param，加了反而容易报错

    @Select({"SELECT " +
            "emp_id empId, emp_name empName, emp_email empEmail, gender,department_id departmentId " +
            "FROM" +
            " tbl_emp LIMIT #{offset},#{limit}"})
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select({"SELECT count(*) FROM tbl_emp"})
    int countEmps();


}
