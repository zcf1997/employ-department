package my.Mapper;

import my.Bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentMapper {

    @Insert({"insert into tbl_dept (dept_id , dept_name, dept_leader) values(#{deptId}, #{deptName}, #{deptLeader})"})
    int insertOne(Department department);

    //最好不要断行，连着写最安全，传对象进去最安全
    @Update({" update tbl_dept set dept_name=#{deptName}, dept_leader=#{deptLeader} where dept_id=#{deptId}"})
    int updateOneByDept_id( Department department);

    @Delete({"delete from tbl_dept where dept_id=#{deptId}"})
    int deleteOneByDept_id( Integer deptId);





    @Select({"select dept_id , dept_name, dept_leader from tbl_dept where dept_id=#{deptId}"})
    Department selectOneByDept_id(Integer deptId);
    @Select({"select dept_id , dept_name, dept_leader from tbl_dept where dept_name=#{deptName}"})
    Department selectOneByDept_name( String deptName);
    @Select({"select dept_id , dept_name, dept_leader from tbl_dept where dept_leader=#{deptLeader}"})
    Department selectOneByDept_leader( String deptLeader);
    @Select({"select dept_id , dept_name, dept_leader from tbl_dept"})
    List<Department> selectDeptList();

    @Select({"SELECT " +
            "dept_id deptId, dept_name deptName, dept_leader deptLeader " +
            "FROM" +
            " tbl_dept LIMIT #{offset},#{limit}"})
    List<Department> selectDeptByLimitAndOffset(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select({"select count(dept_id) from tbl_dept where deptName=#{deptName} or deptLeader=#{deptLeader}"})
    int checkDeptExistsByNameAndLeader(@Param("deptName") String deptName, @Param("deptLeader") String deptLeader);
    @Select({"select count(*) from tbl_dept"})
    int countDepts();
}
