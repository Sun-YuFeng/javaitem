import java.util.Random;
class Group {
    private String groupName;
    private Student[] students;

    public Group(String groupName, Student[] students) {
        this.groupName = groupName;
        this.students = students;
    }

    public String getGroupName() {
        return groupName;
    }

    public Student[] getStudents() {
        return students;
    }

    public Student randomStudent() {
        Random random = new Random();
        if (students.length > 0) {
            int index = random.nextInt(students.length);
            return students[index];
        }
        return null;
    }
}
