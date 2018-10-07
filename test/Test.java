
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.collection.internal.PersistentSet;
import poly.app.core.entities.ChuyenDe;


public class Test {
  public static void main(String args[]) {
      a();
  }
  
  static int a(){
      try {
          int a = 3;
          return a;
      } catch (Exception e) {
      }finally{
          System.out.println("a");
      }
      return -1;
  }
}