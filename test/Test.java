
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import poly.app.core.entities.ChuyenDe;


public class Test {
  public static void main(String args[]) {
      Test a = new Test();
      System.out.println("huhu");
//      try {
//          a.wait();
//      } catch (InterruptedException ex) {
//          Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      a.b();
      System.out.println("1111");
  }
  
  void b(){
      new Thread(() -> {
          System.out.println("haha");
          notify();
          System.out.println("hixhix");
      }).start();
  }
}