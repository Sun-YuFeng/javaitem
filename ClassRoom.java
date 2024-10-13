import java.util.Random;
class ClassRoom {
    public Student[] students;
    public Group[] groups;

    public ClassRoom() {
        students = new Student[100];
        groups = new Group[50];
        for (int i = 0; i < 50; i++) {
            groups[i] = new Group();
        }
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    public Group getRandomGroup() {
        Random random = new Random();
        int index = random.nextInt(50);
        while (groups[index].getStudent1() == null && groups[index].getStudent2() == null) {
            index = random.nextInt(50);
        }
        return groups[index];
    }

    public Student getRandomStudent() {
        Random random = new Random();
        if (students.length > 0) {
            int index = random.nextInt(students.length);
            while (students[index] == null) {
                index = random.nextInt(students.length);
            }
            return students[index];
        } else {
            return null;
        }
    }

    // 添加一个方法来遍历 groups
    public void iterateGroups() {
        for (Group group : groups) {
            if (group!= null) {
                // 可以在这里对每个小组进行操作
            }
        }
    }
}

