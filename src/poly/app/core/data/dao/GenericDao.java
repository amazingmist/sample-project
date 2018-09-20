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
    void insert(T entity);
    void update(T entity);
    boolean delete(T entity);
    int multipleDelete(List<T> entities);
}
