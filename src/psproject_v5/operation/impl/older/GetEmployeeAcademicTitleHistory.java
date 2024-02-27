/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl.older;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.IEntity;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class GetEmployeeAcademicTitleHistory extends AbstractOperation {

    private Employee employee;

    public GetEmployeeAcademicTitleHistory(Object o) throws Exception {
        super();
        if (o instanceof Employee e) {
            employee = e;
        } else {
            throw new Exception("parameter must be instance of Employee");
        }
    }

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<EmployeeAcademicTitle> history = repository.getAllFilter(new EmployeeAcademicTitle(),employee);
        history.sort(new Comparator<EmployeeAcademicTitle>() {
                @Override
                public int compare(EmployeeAcademicTitle o1, EmployeeAcademicTitle o2) {
                    if (o1.getBeginDate() != null && o2.getBeginDate() != null) {
                        return o1.getBeginDate().compareTo(o2.getBeginDate());
                    }
                    if (o1.getBeginDate() != null && o2.getBeginDate() == null) {
                        return 1;
                    }
                    if (o2.getBeginDate() != null && o1.getBeginDate() == null) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        System.out.println(history);
        ((ArrayList<EmployeeAcademicTitle>) param).addAll(history);
        System.out.println("param: " + param);
    }

}
