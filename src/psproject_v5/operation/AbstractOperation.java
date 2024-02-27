/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.operation;

import java.sql.Connection;

import psproject_v5.repository.db.impl.GenericRepository;
import psproject_v5.repository.db.IDBRepository;
import psproject_v5.repository.IRepository;



/**
 *
 * @author aleks
 */
public abstract class AbstractOperation {
    protected final IRepository repository;
    private Connection connection;
    public AbstractOperation() throws Exception {
            this.repository = new GenericRepository();        
    }

    public final void execute(Object param) throws Exception {
        try {
            startTransaction();
            preconditions(param);
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }


    protected abstract void preconditions(Object param) throws Exception;

    private void startTransaction() throws Exception {
        connection = ((IDBRepository)repository).getConnection();
        ((GenericRepository)repository).openConnection(connection);
    }

    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((IDBRepository)repository).commitTransaction(connection);
    }

    private void rollbackTransaction() throws Exception {
        ((IDBRepository)repository).rollbackTransaction(connection);
    }

    private void disconnect() throws Exception {
        ((IDBRepository) repository).closeConnection(connection);
    }
}
