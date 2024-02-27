/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl.older;

import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.Department;
import psproject_v5.domain.Employee;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class GetAllEmployees extends AbstractOperation{

    public GetAllEmployees() throws Exception {
        super();
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Employee> employees = repository.getAll(new Employee());
//        System.out.println(employees);
        ((ArrayList<Employee>) param).addAll(employees);
//        System.out.println("param: "+param);
        
    }
    
}
