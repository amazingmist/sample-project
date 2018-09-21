/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.daoimpl;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import poly.app.core.dao.NhanVienDao;

/**
 *
 * @author vothanhtai
 */
public class NhanVienDaoImplNGTest {
    
    public NhanVienDaoImplNGTest() {
    }

    @Test
    public void testLogin() {
        NhanVienDao nhanVienDao = new NhanVienDaoImpl();
        boolean check = nhanVienDao.checkLogin("NoPT", "123456");
    }
    
}
