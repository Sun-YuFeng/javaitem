import java.util.Random;
class ClassRoom {
    private String className;
    private Group[] groups;
    private Student[] students;

    public ClassRoom(String className, Group[] groups, Student[] students) {
        this.className = className;
        this.groups = groups;
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public Group[] getGroups() {
        return groups;
    }

    public Student[] getStudents() {
        return students;
    }

    public Group randomGroup() {
        Random random = new Random();
        if (groups.length > 0) {
            int index = random.nextInt(groups.length);
            return groups[index];
        }
        return null;
    }

    public Student randomStudentFromClass() {
        Random random = new Random();
        if (students.length > 0) {
            int index = random.nextInt(students.length);
            return students[index];
        }
        return null;
    }
}

