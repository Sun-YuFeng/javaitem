package Service;

import Members.Student;
import Uitil.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServince {

    public void initStudent() throws Exception{
        List<Student> students = readAll();
        students.forEach(student -> {
            Constant.students = students;
        });
    }


    public List<Student> readAll() throws Exception{
        BufferedReader bf = new BufferedReader(new FileReader(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt"));
        String line = bf.readLine();
        List<Student> students = new ArrayList<>();
        while(line != null){
            String[] split = line.split(",");
            Student student = new Student();
            student.setName(split[0]);
            student.setId(Integer.parseInt(split[1]));
            student.setGroupId(Integer.parseInt(split[2]));
            students.add(student);
            line = bf.readLine();
        }
        return students;
    }

    public boolean addStudent(String name,String id,String groupId){
        try {
            BufferedReader br = new BufferedReader(new FileReader(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt"));
            String studentLine = name + ',' + id + ',' + groupId;
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals(studentLine)){
                    return false;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt",true));
            bw.write(studentLine);
            bw.newLine();
            bw.flush();
            bw.close();
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public void changeStudent(String oldName,String newName,String newId,String newGroupId){
        try {
            File oldFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt");
            File newFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\ovo.txt");
            BufferedReader bf = new BufferedReader(new FileReader(oldFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile,true));
            String line;
            while((line = bf.readLine()) != null){
                String[] split = line.split(",");
                if(split[0].equals(oldName)){
                    bw.write(newName + ',' +  newId + ',' + newGroupId);
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

            if(oldFile.delete()){
                newFile.renameTo(new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt"));
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(String studentName){
        try {
            File oldFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt");
            File newFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\ovo.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(oldFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile,true));
            String line = bufferedReader.readLine();
            while(line != null){
                String[] split = line.split(",");
                if(split[0].equals(studentName)){
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
            newFile.renameTo(new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\学生.txt"));

        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
