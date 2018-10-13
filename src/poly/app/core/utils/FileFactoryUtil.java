package poly.app.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FileFactoryUtil {
    public static <T> T read(T data, String path){        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
        
            data = (T) ois.readObject();
        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(ois != null)
                    ois.close();

                if(fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }
    
    public static <T> boolean write(T data, String path){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean isSuccess = true;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(data);
        } catch (IOException ex) {
            isSuccess = false;
            ex.printStackTrace();
        }finally{
            try {
                if(oos != null)
                    oos.close();

                if(fos != null)
                    fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return isSuccess;
    }
    
    public static void main(String[] args) {
        Map<String, String> account = new HashMap<String, String>();
        account.put("username", "a");
        account.put("password", "b");
        
        FileFactoryUtil.write(HashMap.class, new File("accounnt.bin").getAbsolutePath());
    }
}
