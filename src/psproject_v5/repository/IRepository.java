/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package psproject_v5.repository;

import java.util.List;

/**
 *
 * @author aleks
 */
public interface IRepository <T> {
    Object add(T object) throws Exception;
    void edit(T object) throws Exception;
    void delete(T object) throws Exception;
    List<T> getAll(T t) throws Exception;
    List<T> getAllFilter(T t,T filter) throws Exception;
}
