package Service;

import java.io.*;

import Uitil.*;
import Members.*;

import javax.imageio.IIOException;


public class ClassService {
    public File[] listClassNames(){
        return new File(Constant.FILE_PATH).listFiles();
    }

    public void addClass(String className){
        try {
            File file = new File(Constant.FILE_PATH + className);
            file.mkdir();
            File newStudents = new File(Constant.FILE_PATH + className + "\\学生.txt");
            File newGroups = new File(Constant.FILE_PATH + className + "\\小组.txt");
            newStudents.createNewFile();
            newGroups.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void outScore(){
        try {
            File file = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\成绩.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Student student:Constant.students) {
                String line = student.getName() + ',' + student.getId() + ',' + student.score;
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
