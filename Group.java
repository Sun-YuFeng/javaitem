import java.util.Random;

public class Group {
    public String groupName;

    public int groupId;

    public Student[] students = new Student[3];

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void randomCallStudent(){
        Random random = new Random();
        int luckyId = random.nextInt(1)+1;
        String name = students[luckyId].getName();
        int id = students[luckyId].getId();
        int groupId = students[luckyId].getGroupId();
        System.out.println(name+ " " + id+ " " + groupId);
    }


}
