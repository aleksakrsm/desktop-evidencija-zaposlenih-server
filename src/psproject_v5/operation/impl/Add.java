/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation.impl;

import java.util.List;
import psproject_v5.domain.IEntity;
import psproject_v5.exception.RepositoryException;
import psproject_v5.operation.AbstractOperation;

/**
 *
 * @author aleks
 */
public class Add extends AbstractOperation{

    public Add() throws Exception {
        super();
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        IEntity entity = null;
        if(param instanceof IEntity iEntity) {
            entity = iEntity;
        } else{
            throw new RepositoryException("parameter must be instance of IEntity");
        }
        //proveriti da li vec postoji u bazi
        List<IEntity> entitys = repository.getAllFilter(entity, entity);
        if(entitys == null || !entitys.isEmpty()){
            throw new RepositoryException("entity is already saved in database");
        }
        //ogranicenja
        entity.checkSimpleValueRestrictions();
        entity.checkComplexValueRestrictions();
        entity.checkStructuralRestrictions();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add(param);
    }
    
}
