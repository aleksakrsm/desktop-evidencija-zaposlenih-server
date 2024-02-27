/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.parser.Entity;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.IEntity;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class GetAllFilter extends AbstractOperation {

    private IEntity entity;
    private IEntity filter;

    public GetAllFilter(IEntity entity, IEntity filter) throws Exception {
        super();
        this.entity = entity;
        this.filter = filter;
    }

    @Override
    protected void preconditions(Object param) throws Exception {
//        if(entity instanceof Department) return;
//        if(entity instanceof AcademicTitle) return;
//        if(entity instanceof EducationTitle) return;
//        else
//            throw new Exception("employee can be filtered only by department, acadenic od education title");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<IEntity> entitys = repository.getAllFilter(entity,filter);
        ((ArrayList<IEntity>) param).addAll(entitys);
    }

}
