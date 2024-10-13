//CNS (Call Name System 点名系统)
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("张三", "男");
        Student student2 = new Student("李四", "女");
        Student student3 = new Student("王五", "男");
        Student student4 = new Student("赵六", "男");
        Student student5 = new Student("孙七", "女");

        Student[] group1Students = {student1, student2};
        Group group1 = new Group("第一小组", group1Students);

        Student[] group2Students = {student3, student4};
        Group group2 = new Group("第二小组", group2Students);

        Group[] groups = {group1, group2};

        Student[] allStudents = {student1, student2, student3, student4, student5};

        ClassRoom classRoom = new ClassRoom("一班", groups, allStudents);

        Group randomGroup = classRoom.randomGroup();
        if (randomGroup!= null) {
            System.out.println("随机抽取的小组：" + randomGroup.getGroupName());
        }

        Student randomStudentInGroup = randomGroup.randomStudent();
        if (randomStudentInGroup!= null) {
            System.out.println("随机抽取的小组中的学生：" + randomStudentInGroup.getName());
        }

        Student randomStudentInClass = classRoom.randomStudentFromClass();
        if (randomStudentInClass!= null) {
            System.out.println("随机从全班抽取的学生：" + randomStudentInClass.getName());
        }
    }
}