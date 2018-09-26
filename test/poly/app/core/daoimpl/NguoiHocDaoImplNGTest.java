/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.daoimpl;

import java.util.List;
import org.testng.annotations.Test;
import poly.app.core.dao.NguoiHocDao;
import poly.app.core.entities.NguoiHoc;
import poly.app.core.entities.NhanVien;
import poly.app.core.helper.DateHelper;

/**
 *
 * @author vothanhtai
 */
public class NguoiHocDaoImplNGTest {
    
    public NguoiHocDaoImplNGTest() {
    }

    
//    @Test
    public void testInsert() {
        NguoiHocDao nguoiHocDao = new NguoiHocDaoImpl();
        NhanVien nhanVien = new NhanVienDaoImpl().getById("TaiVT");
        NguoiHoc nguoiHoc = new NguoiHoc("PS06631", "Võ Thành Tài", 
                DateHelper.toDate("07/17/1997") , true, "093123123", "thanhtai@gmail.com", "", nhanVien, DateHelper.now());
        nguoiHocDao.insert(nguoiHoc);
    }
    
//    @Test
    public void testUpdate() {
        NguoiHocDao nguoiHocDao = new NguoiHocDaoImpl();
        NhanVien nhanVien = new NhanVienDaoImpl().getById("TaiVT");
        NguoiHoc nguoiHoc = new NguoiHoc("PS06631", "Võ Thành Tài", 
                DateHelper.toDate("07/17/1997") , true, "0932938178", "thanhtai17071997@gmail.com", "0932938178 - VO THANH TAI", nhanVien, DateHelper.now());
        nguoiHocDao.update(nguoiHoc);
    }
    
//    @Test
    public void testDelete() {
        NguoiHocDao nguoiHocDao = new NguoiHocDaoImpl();
        nguoiHocDao.deleteById("PS06631");
    }
    
    @Test
    public static void testGetAll() {
        NguoiHocDao nguoiHocDao = new NguoiHocDaoImpl();
        List<NguoiHoc> list = nguoiHocDao.getAll();
        for (NguoiHoc nguoiHoc : list) {
            System.out.println(nguoiHoc.getMaNh() + " - " + nguoiHoc.getHoTen());
        }
    }
    
    
}
