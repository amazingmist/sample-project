/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.dao;

import poly.app.core.data.dao.GenericDao;
import poly.app.core.entities.NhanVien;

/**
 *
 * @author vothanhtai
 */
public interface NhanVienDao extends GenericDao<String, NhanVien>{
    NhanVien checkLogin(String maNv, String matKhau);
    NhanVien getNhanVienByEmail(String email);
}
