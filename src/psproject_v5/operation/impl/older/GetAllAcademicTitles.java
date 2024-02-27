/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl.older;

import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.Employee;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class GetAllAcademicTitles extends AbstractOperation{

    public GetAllAcademicTitles() throws Exception {
        super();
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<AcademicTitle> titles = repository.getAll(new AcademicTitle());
//        System.out.println(titles);
        ((ArrayList<AcademicTitle>) param).addAll(titles);
//        System.out.println("param: "+param);
        
    }
    
}
