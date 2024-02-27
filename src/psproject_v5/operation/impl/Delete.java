/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl;

import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.exception.RepositoryException;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class Delete extends AbstractOperation{

    public Delete() throws Exception {
        super();
    }

    
    
    @Override
    protected void preconditions(Object param) throws Exception {

        List<EmployeeAcademicTitle> list = repository.getAllFilter(param, param);
        if(list == null || list.isEmpty())
            throw new RepositoryException("trazeni slog ne postoji u bazi");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete(param);
    }
    
}
