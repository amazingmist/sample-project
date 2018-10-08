package poly.app.core.daoimpl;

import java.util.List;
import org.hibernate.Session;
import poly.app.core.dao.NguoiHocDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.NguoiHoc;
import org.hibernate.HibernateException;

public class NguoiHocDaoImpl extends AbstractDao<String, NguoiHoc> implements NguoiHocDao {

    @Override
    public List<Object[]> selectNguoiHocNotInKhoaHoc(int maKh) {
        List<Object[]> result = null;
        Session session = this.getSession();
        try {
            String sql = "SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = :maKh)";
            result = session.createSQLQuery(sql).setParameter("maKh", maKh).list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    
    public static void main(String[] args) {
        new NguoiHocDaoImpl().selectNguoiHocNotInKhoaHoc(2);
    }
}
