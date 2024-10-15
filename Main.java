import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("请输入班级人数:");
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        Class ClassA = new Class();
        for(int i = 1;i <= n;i++){
            ClassA.students[i] = new Student();
            ClassA.students[i].setAge(18);
        }
        ClassA.students[1].setName("Li");
        ClassA.students[1].setId(2023015797);
        ClassA.students[1].setGroupId(1);

        ClassA.students[2].setName("Miao");
        ClassA.students[2].setId(2023015798);
        ClassA.students[2].setGroupId(1);

        ClassA.students[3].setName("Fan");
        ClassA.students[3].setId(2023015799);
        ClassA.students[3].setGroupId(2);

        ClassA.students[4].setName("Liu");
        ClassA.students[4].setId(2023015800);
        ClassA.students[4].setGroupId(2);

        ClassA.students[5].setName("Xin");
        ClassA.students[5].setId(2023015801);
        ClassA.students[5].setGroupId(3);

        ClassA.students[6].setName("Lu");
        ClassA.students[6].setId(2023015802);
        ClassA.students[6].setGroupId(3);

        ClassA.students[7].setName("Yu");
        ClassA.students[7].setId(2023015803);
        ClassA.students[7].setGroupId(4);

        ClassA.students[8].setName("Kang");
        ClassA.students[8].setId(2023015804);
        ClassA.students[8].setGroupId(4);
        ClassA.randomCallStudent(n);


        for(int i = 1;i <= n/2;i++){
            ClassA.groups[i] = new Group();
            ClassA.groups[i].groupId = i;
            ClassA.groups[i].students[1] = new Student();
            ClassA.groups[i].students[2] = new Student();
            ClassA.groups[i].students[1].setGroupId(i);
            ClassA.groups[i].students[2].setGroupId(i);
            ClassA.groups[i].students[1].setAge(18);
            ClassA.groups[i].students[2].setAge(18);
            ClassA.groups[i].groupName = "第" + i + "组";
        }
        ClassA.groups[1].students[1].setName("Li");
        ClassA.groups[1].students[1].setId(2023015797);

        ClassA.groups[1].students[2].setName("Miao");
        ClassA.groups[1].students[2].setId(2023015798);

        ClassA.groups[2].students[1].setName("Fan");
        ClassA.groups[2].students[1].setId(2023015799);

        ClassA.groups[2].students[2].setName("Liu");
        ClassA.groups[2].students[2].setId(2023015800);

        ClassA.groups[3].students[1].setName("Xin");
        ClassA.groups[3].students[1].setId(2023015801);

        ClassA.groups[3].students[2].setName("Lu");
        ClassA.groups[3].students[2].setId(2023015802);

        ClassA.groups[4].students[1].setName("Yu");
        ClassA.groups[4].students[1].setId(2023015803);

        ClassA.groups[4].students[2].setName("Kang");
        ClassA.groups[4].students[2].setId(2023015804);

        int idx = ClassA.randomCallGroup(n/2);
        ClassA.groups[idx].randomCallStudent();
    }
}