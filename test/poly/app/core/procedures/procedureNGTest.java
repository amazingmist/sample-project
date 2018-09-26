/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.procedures;

import java.util.List;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author vothanhtai
 */
public class procedureNGTest {

    public procedureNGTest() {
    }

//    @Test
    public void testSPBangDiem() {
        List list = new sp_BangDiem(1).execute();
        for (Object object : list) {
            System.out.println(object);
        }
    }

//    @Test
    public void testSPThongKeDiem() {
        List list = new sp_ThongKeDiem().execute();
        for (Object object : list) {
            System.out.println(object);
        }
    }

//    @Test
    public void testSPThongKeDoanhThu() {
        List list = new sp_ThongKeDoanhThu(2018).execute();
        for (Object object : list) {
            System.out.println(object);
        }
    }

//    @Test
    public void testSPThongKeNguoiHoc() {
        List list = new sp_ThongKeNguoiHoc().execute();
        for (Object object : list) {
            System.out.println(object);
        }
    }

}
