/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package psproject_v5.repository.db;

import java.sql.Connection;
import java.sql.SQLException;
import psproject_v5.connection.ConnectionPool;
import psproject_v5.repository.IRepository;

/**
 *
 * @author aleks
 */
public interface IDBRepository<T> extends IRepository<T>{
    default Connection getConnection() throws Exception{
        return ConnectionPool.getInstance().getConnection();
    }
    default void rollbackTransaction(Connection connection)throws SQLException{
        connection.rollback();
    }
    default void commitTransaction(Connection connection) throws SQLException{
        connection.commit();
    }
    default void closeConnection(Connection connection){
        ConnectionPool.getInstance().returnConnectionToPool(connection);
    }
}
