/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl;

import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.Department;
import psproject_v5.domain.Employee;
import psproject_v5.domain.IEntity;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class GetAll extends AbstractOperation{
    private IEntity entity;
    public GetAll(IEntity entity) throws Exception {
        super();
        this.entity = entity;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<IEntity> employees = repository.getAll(entity);
        ((ArrayList<IEntity>) param).addAll(employees);
    }
    
}
