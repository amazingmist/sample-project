/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.daoimpl;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import poly.app.core.entities.KhoaHoc;

/**
 *
 * @author vothanhtai
 */
public class KhoaHocDaoImplNGTest {
    
    public KhoaHocDaoImplNGTest() {
    }

    @Test
    public void testSelectAll() {
        List<KhoaHoc> list = new KhoaHocDaoImpl().selectAll();
        for (KhoaHoc khoaHoc : list) {
            System.out.println(khoaHoc.getChuyenDe().getTenCd());
        }
    }
    
}
