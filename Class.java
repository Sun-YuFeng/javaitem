import java.util.Random;

public class Class {
    Student[] students = new Student[51];

    Group[] groups = new Group[26];
    public void randomCallStudent(int n) {
        Random random = new Random();
        int luckyId = random.nextInt(n)+1;
        String name = students[luckyId].getName();
        int id = students[luckyId].getId();
        int groupId = students[luckyId].getGroupId();
        System.out.println(name+ " " + id+ " " + groupId);
    }

    public int randomCallGroup(int n){
        Random random = new Random();
        int luckyId = random.nextInt(n)+1;
        String name = groups[luckyId].groupName;
        int id = groups[luckyId].groupId;
        System.out.println(name + " " + id);
        return id;
    }

}
