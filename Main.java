//CNS (Call Name System 点名系统)
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassRoom classroom = new ClassRoom();
        Scanner scanner = new Scanner(System.in);
        boolean isNaming = true;
        while (true) {
            boolean a=true;
            if(a) {
                System.out.println("请输入操作：1-写入学生入组，2-抽取小组，3-在班级里随机抽人");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:

                        System.out.println("请输入学生姓名：");
                        String name = scanner.next();
                        System.out.println("请输入学生性别：");
                        String gender = scanner.next();
                        Student student = new Student(name, gender);
                        classroom.addStudent(student);
                        boolean added = false;
                        classroom.iterateGroups();
                        for (Group group : classroom.groups) {
                            if (group.addStudent(student)) {
                                added = true;
                                break;
                            }
                        }
                        if (!added) {
                            System.out.println("班级已满，无法添加学生。");
                            break;
                        } else {
                            if (isNaming) {
                                System.out.println("请为该小组输入一个名字：");
                                String groupName = scanner.next();
                                for (Group group : classroom.groups) {
                                    if (group.getStudent1() == student || group.getStudent2() == student) {
                                        group.setGroupName(groupName);
                                        break;
                                    }
                                }
                            }
                            System.out.println("小组还要进人吗？如果要，则输入 4，继续写入；不要则输入其他任意字符。");
                            int continueChoice = scanner.nextInt();
                            if (continueChoice == 4) {
                                boolean groupFull = true;
                                for (Group group : classroom.groups) {
                                    if (group.getStudent1() == null || group.getStudent2() == null) {
                                        groupFull = false;
                                        break;
                                    }
                                }
                                if (groupFull) {
                                    System.out.println("所有小组已满，无法继续添加学生。");
                                } else {
                                    System.out.println("请输入学生姓名：");
                                    String newName = scanner.next();
                                    System.out.println("请输入学生性别：");
                                    String newGender = scanner.next();
                                    Student newStudent = new Student(newName, newGender);
                                    classroom.addStudent(newStudent);
                                    added = false;
                                    for (Group group : classroom.groups) {
                                        if (group.addStudent(newStudent)) {
                                            added = true;
                                            break;
                                        }
                                    }
                                    if (!added) {
                                        System.out.println("小组已满，无法继续添加学生。");
                                    }
                                }
                            }
                            else{
                                System.out.println("输入有问题");
                            }
                        }
                        break;
                    case 2:
                        Group randomGroup = classroom.getRandomGroup();
                        System.out.println("随机抽取的小组：名字为 " + randomGroup.getGroupName());
                        Student randomStudentInGroup = randomGroup.getRandomStudent();
                        if (randomStudentInGroup != null) {
                            System.out.println("学生姓名：" + randomStudentInGroup.getName() + "，性别：" + randomStudentInGroup.getGender());
                        } else {
                            System.out.println("该小组为空。");
                        }
                        break;
                    case 3:
                        Student randomStudent = classroom.getRandomStudent();
                        if (randomStudent != null) {
                            System.out.println("随机抽取的学生：姓名：" + randomStudent.getName() + "，性别：" + randomStudent.getGender());
                        } else {
                            System.out.println("班级中没有学生。");
                        }
                        break;
                    default:
                        System.out.println("无效输入。");
                }
            }
            System.out.println("你要再次点名或者写入吗？不要就输入6(输入数字）");
            int b=scanner.nextInt();
            if(b==6){
                a=false;
            }
            else{
                a=true;
            }
        }
    }
}