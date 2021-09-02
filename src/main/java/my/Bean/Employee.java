package my.Bean;


public class Employee {
    /*定义employee这个类对象的属性，属性要跟数据库中的表属性一致，名称可不一致
    属性的getter、setter方法,无参方法
    同时要重写tostring方法输出
     */
    private Integer empId;
    private String empName;
    private String empEmail;
    private String gender;
    private Integer departmentId;
    private String department;

    public int getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + empId +
                ", name='" + empName + '\'' +
                ", department=" + department +
                '}';
    }
    //注入方法：有参+set方法
    public Employee(Integer empId,String empName,String empEmail,String gender,Integer departmentId){
        this.empId=empId;
        this.empName=empName;
        this.empEmail=empEmail;
        this.gender=gender;
        this.departmentId=departmentId;



    }
}
