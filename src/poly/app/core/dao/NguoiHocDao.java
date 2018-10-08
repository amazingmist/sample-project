/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.dao;

import java.util.List;
import poly.app.core.data.dao.GenericDao;
import poly.app.core.entities.NguoiHoc;

/**
 *
 * @author vothanhtai
 */
public interface NguoiHocDao extends GenericDao<String, NguoiHoc>{
    public List<Object[]> selectNguoiHocNotInKhoaHoc(int maKh);
}
