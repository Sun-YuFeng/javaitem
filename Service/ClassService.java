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
            File[] files = new File(Constant.FILE_PATH + Constant.CLASS_PATH).listFiles();
            boolean flag = false;
            for (File f:files) {
                if(f.getName().equals("成绩.txt")){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                File file = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\成绩.txt");
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                for (Student student:Constant.students) {
                    String line = student.getName() + ',' + student.getId() + ',' + student.score;
                    bw.write(line);
                    bw.newLine();
                }
                System.gc();
                Thread.sleep(200);

                bw.flush();
                bw.close();
            }else{
                File file = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\成绩.txt");
                File newFile = new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\ovo.txt");
                BufferedWriter nbw = new BufferedWriter(new FileWriter(newFile,true));
                BufferedReader nbr = new BufferedReader(new FileReader(file));
                String line = nbr.readLine();
                while(line != null){
                    String[] split = line.split(",");
                    for (Student student:Constant.students){
                        if(student.getId() == Integer.parseInt(split[1])){
                            int newScore = Integer.parseInt(split[2]) + student.getScore();
                            nbw.write(split[0] + "," + split[1] + "," + newScore);
                            nbw.newLine();
                        }
                    }
                    line = nbr.readLine();
                }
                nbw.flush();
                nbw.close();
                nbr.close();

                System.gc();
                Thread.sleep(200);

                if(file.exists()){
                    file.delete();
                    newFile.renameTo(new File(Constant.FILE_PATH + Constant.CLASS_PATH + "\\成绩.txt"));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
