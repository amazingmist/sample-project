package poly.app.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import poly.app.core.entities.ChuyenDe;

public class DataTypeConverterUtil {

    public static <T> Vector objectToVector(T object) throws Exception{
        Vector result = new Vector();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        try {
            for (int i = 0; i < fields.length; i++) {
//                Get current field
                Field curField = clazz.getDeclaredField(fields[i].getName());

//                Set can accesible this field
                curField.setAccessible(true);

//                Get and add value to vector
                result.add(curField.get(object));

//                Set can not accesible this field
                curField.setAccessible(false);
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            throw ex;
        }

        return result;
    }

    public static <T> Vector objectToVectorByFields(T object, String[] objectFeilds) throws Exception {
        Vector result = new Vector();
        Class<?> clazz = object.getClass();
        
        try {
            for (String fieldName : objectFeilds) {
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = clazz.getDeclaredMethod(getMethodName);
                result.add(method.invoke(object));
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            throw ex;
        }

        return result;
    }

    public static <T> Vector objectListToVectorByFields(List<T> list, String[] objectFeilds) throws Exception{
        Vector result = new Vector();
        for (T t : list) {
            try {
                result.add(DataTypeConverterUtil.objectToVectorByFields(t, objectFeilds));
            } catch (Exception ex) {
                throw ex;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        ChuyenDe chuyenDe = new ChuyenDe("TAI01", "Học lập trình C#", 3000, 10, "tai.png", "Học C#");
        try {
            Vector v = DataTypeConverterUtil.objectToVectorByFields(chuyenDe, new String[]{"maCd"});
            System.out.println(v);
        } catch (Exception ex) {
            Logger.getLogger(DataTypeConverterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
