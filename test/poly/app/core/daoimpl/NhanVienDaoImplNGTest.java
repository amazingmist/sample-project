/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.daoimpl;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import poly.app.core.dao.NhanVienDao;
import poly.app.core.entities.NhanVien;

/**
 *
 * @author vothanhtai
 */
public class NhanVienDaoImplNGTest {
    
    public NhanVienDaoImplNGTest() {
    }

//    @Test
    public void testLogin() {
        NhanVienDao nhanVienDao = new NhanVienDaoImpl();
        nhanVienDao.checkLogin("NoPT", "123456");
    }
    
    @Test
    public void testUpdate() {
        NhanVienDao nhanVienDao = new NhanVienDaoImpl();
        NhanVien nhanVien = new NhanVien("TaiVT", "123", "VTT", "thanhtai17071997@gmail.com", true);
        boolean check = nhanVienDao.update(nhanVien);
    }
    
}
