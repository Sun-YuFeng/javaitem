import java.util.Random;
class Group {
    public Student student1;
    public Student student2;
    private String groupName;

    public Group() {
        student1 = null;
        student2 = null;
        groupName = "";
    }

    public boolean addStudent(Student student) {
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