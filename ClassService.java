package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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


}
