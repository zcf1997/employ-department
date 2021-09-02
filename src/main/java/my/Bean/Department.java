package my.Bean;

public class Department {
    private Integer deptId;
    private String deptName;
    private String deptLeader;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int id) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String name) {
        this.deptName = deptName;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String leader) {
        this.deptLeader = deptLeader;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", name='" + deptName + '\'' +
                ", leader='" + deptLeader + '\'' +
                '}';
    }
    //有参构造方法
    public Department(Integer deptId, String deptName, String deptLeader){
        this.deptId=deptId;
        this.deptName=deptName;
        this.deptLeader=deptLeader;

    }
}
