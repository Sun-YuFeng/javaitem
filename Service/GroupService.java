package Service;

import Members.Group;
import Members.Student;
import Uitil.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupService {

    public void initGroupAndStudent() throws Exception{
        List<Group> groups = readAll();
        groups.forEach(group -> {
            Constant.groups.put(group,new ArrayList<>());
        });
    }

    public List<Group> readAll() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt"));
            String line = bf.readLine();
            List<Group> groupList = new ArrayList<>();
            while (line != null) {
                String[] split = line.split(",");
                Group group = new Group();
                group.setGroupId(Integer.parseInt(split[0]));
                group.setGroupName(split[1]);
                group.setScore(Integer.parseInt(split[2]));
                groupList.add(group);
                line = bf.readLine();
            }
            return groupList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addGroup(String groupName,int score){
        try {
            File file = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int number = 1;
            String bfLine = bufferedReader.readLine();
            while(bfLine != null){
                number++;
                bfLine = bufferedReader.readLine();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt",true));
            String bwLine = String.valueOf(number) + ',' + groupName + ',' + score;
            bufferedWriter.write(bwLine);
            bufferedWriter.newLine();
            bufferedWriter.flush();;
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deleteGroup(String groupName){
        try {
            File oldFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt");
            File newFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\ovo.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(oldFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile,true));
            String line = bufferedReader.readLine();
            while(line != null){
                String[] split = line.split(",");
                if(split[1].equals(groupName)){
                    line = bufferedReader.readLine();
                    continue;
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();

            System.gc();
            Thread.sleep(200);

            oldFile.delete();
            newFile.renameTo(new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt"));

        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeGroup(String oldName,String newName,int newScore){
        try {
            File oldFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt");
            File newFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\ovo.txt");
            BufferedReader bf = new BufferedReader(new FileReader(oldFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile,true));
            String line;
            while((line = bf.readLine()) != null){
                String[] split = line.split(",");
                if(split[1].equals(oldName)){
                    bw.write(split[0] + ',' +newName + ',' + String.valueOf(newScore));
                }else{
                    bw.write(line);
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
            bf.close();

            System.gc();
            Thread.sleep(200);

            oldFile.delete();
            newFile.renameTo(new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt"));
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGroupName(int groupId){
        try {
            BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_PATH + Constant.CLASS_PATH + "\\小组.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                if(split[0].equals(String.valueOf(groupId))){
                    br.close();
                    return split[1];
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public int getGroupScore(String groupName){
        List<Group> groups = new GroupService().readAll();
        for(int i = 0;i < groups.size();i++){
            if(groups.get(i).getGroupName().equals(groupName)){
                return groups.get(i).getScore();
            }
        }
        return -1;
    }

    public String getGroupId(String groupName){
        List<Group> groups = new GroupService().readAll();
        for(int i = 0;i < groups.size();i++){
            if(groups.get(i).getGroupName().equals(groupName)){
                return String.valueOf(groups.get(i).getGroupId());
            }
        }
        return "";
    }


    public String randonCallGroup(){
        List<Group> groups = new GroupService().readAll();
        Random random = new Random();
        int luckyId = random.nextInt(groups.size());
        String luckyGroup = groups.get(luckyId).getGroupName();
        return luckyGroup;
    }

    public String randomCallStudent(String groupId){
        try {
            List<Student> students = new StudentServince().readAll();
            List<Student> trueStudent = new ArrayList<>();
            for (Student s:students) {
                if(s.getGroupId() == Integer.parseInt(groupId)){
                    trueStudent.add(s);
                }
            }
            Random random = new Random();
            int luckyId = random.nextInt(trueStudent.size());
            String luckyStudent = trueStudent.get(luckyId).getName();
            return luckyStudent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
