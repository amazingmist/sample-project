
import java.util.Date;
import poly.app.core.helper.DateHelper;

public class Test {

    public static void main(String[] argv) throws Exception {
        Date d1 = DateHelper.toDate("17-07-1997");
        Date d2 = new Date();
        
        System.out.println(DateHelper.getDiffYears(d1, d2));
        
    }
}
