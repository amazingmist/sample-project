/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.daoimpl;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import poly.app.core.dao.MaXacNhanDao;
import poly.app.core.entities.MaXacNhan;
import poly.app.core.entities.NhanVien;

/**
 *
 * @author vothanhtai
 */
public class MaXacNhanImplNGTest {
    
    public MaXacNhanImplNGTest() {
    }

    @Test
    public void testSaveOrUpate() {
        MaXacNhanDao maXacNhanDao = new MaXacNhanImpl();
        NhanVien nhanVien = new NhanVienDaoImpl().getNhanVienByEmail("thanhtai17071997@gmail.com");
        MaXacNhan maXacNhan = new MaXacNhan(nhanVien, "JHGHJG6575");
        maXacNhanDao.saveOrUpdate(maXacNhan);
    }
    
}
