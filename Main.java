//CNS (Call Name System 点名系统)
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //实例化一个班，后续如果像实例化多个班级，可以再写一个学校类，存放班级
        ClassRoom classroom = new ClassRoom();
        Scanner scanner = new Scanner(System.in);
        boolean isNaming = true;//小组取名
        while (true) {//项目运行用死循环，保证他可以一直执行
            boolean a=true;//点名肯定不止一次，写入学生肯定不止一次
            if(a) {
                System.out.println("请输入操作：1-写入学生入组，2-抽取小组，3-在班级里随机抽人");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:

                        System.out.println("请输入学生姓名：");
                        String name = scanner.next();
                        System.out.println("请输入学生性别：");
                        String gender = scanner.next();
                        Student student = new Student(name, gender);//实例化一个学生
                        classroom.addStudent(student);//把学生写进班级
                        boolean added = false;//把人加入空组，如果写不进去了（没有可以写入的组，说明班级满员了）
                        for (Group group : classroom.groups) {
                            if (group.addStudentgroup(student)) {
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
                                    if (group.getStudent1() == student || group.getStudent2() == student) {//有人小组才有名字
                                        group.setGroupName(groupName);
                                        break;
                                    }
                                }
                            }
                            //小组一个人也可以，看要不要继续写入
                            System.out.println("小组还要进人吗？如果要，则输入 4，继续写入；不要则输入其他任意字符。");
                            int continueChoice = scanner.nextInt();
                            if (continueChoice == 4) {
                                boolean groupFull = true;
                                for (Group group : classroom.groups) {//如果小组满了，那就不能写入了
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
                                        if (group.addStudentgroup(newStudent)) {
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
            System.out.println("你要再次点名或者写入吗(输入除6以外的数字）？不要就输入6");
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