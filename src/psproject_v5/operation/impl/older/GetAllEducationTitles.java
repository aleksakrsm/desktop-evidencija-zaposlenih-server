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
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class GetAllEducationTitles extends AbstractOperation{

    public GetAllEducationTitles() throws Exception {
        super();
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<EducationTitle> titles = repository.getAll(new EducationTitle());
//        System.out.println(titles);
        ((ArrayList<EducationTitle>) param).addAll(titles);
//        System.out.println("param: "+param);
        
    }
    
}
