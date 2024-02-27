/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl.older;

import java.util.ArrayList;
import java.util.List;
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
public class GetAllEmployeesFilter extends AbstractOperation {

    private IEntity entity;

    public GetAllEmployeesFilter(Object o) throws Exception {
        super();
        if (o instanceof IEntity iEntity) {
            entity = iEntity;
        } else {
            throw new Exception("parameter must be instance of IEntity");
        }
    }

    @Override
    protected void preconditions(Object param) throws Exception {
        if(entity instanceof Department) return;
        if(entity instanceof AcademicTitle) return;
        if(entity instanceof EducationTitle) return;
        else
            throw new Exception("employee can be filtered only by department, acadenic od education title");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Employee> employees = repository.getAllFilter(new Employee(),entity);
//        System.out.println(employees);
        ((ArrayList<Employee>) param).addAll(employees);
//        System.out.println("param: " + param);
    }

}
