/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vothanhtai
 */
public interface GenericDao <ID extends Serializable, T>{
    List<T> getAll();
    T getById(ID id);
    public List<T> getByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit);
    boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    boolean deleteById(ID id);
    int multipleDelete(List<T> entities);
    boolean saveOrUpdate(T entity);
}
