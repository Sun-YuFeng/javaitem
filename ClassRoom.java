import java.util.Random;
class ClassRoom {
    //一个班级最基本的就是学生，所以定义学生数组，同时也要有一个数组存入小组
    public Student[] students;
    public Group[] groups;

    //构造方法 实例化一个班级，自动实例化50个空的group(也就是说班级一旦生产，就自动实例化好了五十个组，只不过组是空的
    // 但是可以调用group里的方法写入学生信息来实现学生入组）
    public ClassRoom() {
        students = new Student[100];
        groups = new Group[50];
        for (int i = 0; i < 50; i++) {
            groups[i] = new Group();
        }
    }

    //写入学生进班级，与学生入组不同，每次实例化好了一个学生，优先自动入班，然后再是入组
    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    //随机抽组，抽取小组，因为提前实例化好了50个空的小组，为了确保抽到的小组有人，用while循环
    //一直随机取到小组有人为止
    public Group getRandomGroup() {
        Random random = new Random();
        int index = random.nextInt(50);
        while (groups[index].getStudent1() == null && groups[index].getStudent2() == null) {
            index = random.nextInt(50);
        }
        return groups[index];
    }

    //随机抽学生，抽单人直接在班级里抽，还是要确保抽到的人不为空
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
}

