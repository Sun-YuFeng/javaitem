import java.util.Random;
class Group {
    //一个组里两个人，所以定义两个student
    public Student student1;
    public Student student2;
    private String groupName;

    //构造方法 实例化一个组一定要有两个学生，还有名字
    public Group() {
        student1 = null;
        student2 = null;
        groupName = "";
    }

    //写入学生方法 如果第一个student是null，说明组空，直接写入，不然就看第二个student
    public boolean addStudentgroup(Student student) {
        if (student1 == null) {
            student1 = student;
            return true;
        } else if (student2 == null) {
            student2 = student;
            return true;
        } else {
            return false;
        }
    }

    //组内随机抽人，nextBoolean的返回值是true和false
    //如果组内就一个人，那么直接返回，如果组内有两个，就用三元运算符
    //没学生直接返回null
    public Student getRandomStudent() {
        Random random = new Random();
        if (student1!= null && student2!= null) {
            return random.nextBoolean()? student1 : student2;
        } else if (student1!= null) {
            return student1;
        } else {
            return null;
        }
    }

    //get方法，返回学生这个类，为抽取人，打印结果准备
    public Student getStudent1() {
        return student1;
    }

    public Student getStudent2() {
        return student2;
    }
    public void setGroupName(String name) {
        groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }
}