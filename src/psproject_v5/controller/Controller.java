/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.controller;

import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.IEntity;
import psproject_v5.operation.AbstractOperation;
import psproject_v5.operation.impl.Add;
import psproject_v5.operation.impl.Delete;
import psproject_v5.operation.impl.GetAll;
import psproject_v5.operation.impl.GetAllFilter;
import psproject_v5.operation.impl.SaveOrUpdateList;
import psproject_v5.operation.impl.Update;

/**
 *
 * @author aleks
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<IEntity> getAllFilter(IEntity type,IEntity filter) throws Exception {
        try {
            
            AbstractOperation operation = new GetAllFilter(type,filter);
            List<IEntity> entitys = new ArrayList<>();
            operation.execute(entitys);
            
            return entitys;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public List<IEntity> getAll(IEntity type) throws Exception {
        try {
            
            AbstractOperation operation = new GetAll(type);
            List<IEntity> entitys = new ArrayList<>();
            operation.execute(entitys);
            
            return entitys;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void add(IEntity entity) throws Exception {
        try {
            AbstractOperation operation = new Add();
            operation.execute(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void delete(IEntity entity) throws Exception {
        try {
            AbstractOperation operation = new Delete();
            operation.execute(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void update(IEntity entity) throws Exception {
        try {
            AbstractOperation operation = new Update();
            operation.execute(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }



    public void saveOrUpdateList(List<IEntity> list) throws Exception {
        try {
            AbstractOperation operation = new SaveOrUpdateList();
            operation.execute(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
