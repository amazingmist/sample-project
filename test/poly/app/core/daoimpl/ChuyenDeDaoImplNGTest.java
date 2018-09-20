/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import poly.app.core.dao.ChuyenDeDao;
import poly.app.core.entities.ChuyenDe;

/**
 *
 * @author vothanhtai
 */
public class ChuyenDeDaoImplNGTest {
    
    public ChuyenDeDaoImplNGTest() {
    }

//    @Test
    public void testInsert() {
        ChuyenDeDao chuyenDeDao = new ChuyenDeDaoImpl();
        ChuyenDe chuyenDe = new ChuyenDe("TAI01", "Học lập trình C#", 3000, 10, "tai.png", "Học C#");
        chuyenDeDao.insert(chuyenDe);
    }
    
//    @Test
    public void testUpdate(){
        ChuyenDeDao chuyenDeDao = new ChuyenDeDaoImpl();
        ChuyenDe chuyenDe = new ChuyenDe("TAI01", "Học lập trình java", 3000, 10, "tai.png", "Học java");
        chuyenDeDao.update(chuyenDe);
    }
    
//    @Test
    public void testDelete(){
        ChuyenDeDao chuyenDeDao = new ChuyenDeDaoImpl();
        ChuyenDe chuyenDe = new ChuyenDe("TAI01", "Học lập trình java", 3000, 10, "tai.png", "Học java");
        chuyenDeDao.delete(chuyenDe);
    }
    
//    @Test
    public void testMultipleDelete(){
        ChuyenDeDao chuyenDeDao = new ChuyenDeDaoImpl();
        List<ChuyenDe> list = new ArrayList<>();
        list.add(new ChuyenDe("TAI01", "Học lập trình java", 3000, 10, "tai.png", "Học java"));
        list.add(new ChuyenDe("TAI02", "Học lập trình java", 3000, 10, "tai.png", "Học java"));
        chuyenDeDao.multipleDelete(list);
    }
    
//    @Test
    public void testGetAll() {
        ChuyenDeDao chuyenDeDao = new ChuyenDeDaoImpl();
        List<ChuyenDe> list = chuyenDeDao.getAll();
        for (ChuyenDe chuyenDe : list) {
            // TODO: overide toString method in CHuyenDe
            System.out.println(chuyenDe.getTenCd());
        }
    }
    
//    @Test
    public void testGetById() {
        ChuyenDeDao chuyenDeDao = new ChuyenDeDaoImpl();
        ChuyenDe chuyenDe = chuyenDeDao.getById("JAV01");
    }
}
