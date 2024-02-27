/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl;

import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.Department;
import psproject_v5.domain.IEntity;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class SaveOrUpdateList extends AbstractOperation {

    public SaveOrUpdateList() throws Exception {
        super();
    }

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param instanceof java.util.List) {
            List<Object> list = (List<Object>) param;
            for (Object object : list) {
                if (object instanceof IEntity entity) {
//                  ogranicenja!
                    entity.checkSimpleValueRestrictions();
                    entity.checkComplexValueRestrictions();
                    entity.checkStructuralRestrictions();
                } else {
                    throw new Exception("prosledjena lista nije napunjena objektima IEntity");
                }
            }
        } else {
            throw new Exception("Prosledjeni objekat nije lista");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<IEntity> list = (List<IEntity>) param;
        for (IEntity entity : list) {
            List<IEntity> l = repository.getAllFilter(entity, entity);
//            if ((repository.getAllFilter(entity, entity)) == null || repository.getAllFilter(entity, entity).isEmpty()) {
            if (l == null || l.isEmpty()) {
                repository.add(entity);
                System.out.println("Add : " + entity);
            } else {
                repository.edit(entity);
                System.out.println("Update : " + entity);
            }
        }
    }

}
