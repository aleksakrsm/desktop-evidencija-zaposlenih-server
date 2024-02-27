/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.repository.db.impl;

import psproject_v5.repository.db.IDBRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import psproject_v5.domain.IEntity;

/**
 *
 * @author aleks
 */
public class GenericRepository implements IDBRepository<IEntity> {

    private Connection connection;

    public GenericRepository() throws Exception {

    }
    
    public void openConnection(Connection connection){
        this.connection = connection;
    }
    
    @Override
    public Object add(IEntity object) throws Exception {
        PreparedStatement ps = null;
        try {
            String query = object.getInsertQuery();

            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            object.fillPreparedStatement(ps);

            int rez = ps.executeUpdate();
            Object id = null;
            if (rez != 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = object.getAutoGeneratedID(rs);
                    object.setId(id);
                }
                rs.close();
            }
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();            
            throw ex;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void edit(IEntity object) throws Exception {
        PreparedStatement ps = null;
        try {
            String query = object.getUpdateQuery() + " " + object.getWhereCondition();
            System.out.println(query);
            ps = connection.prepareStatement(query);
            object.fillPreparedStatement(ps);

            int rez = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void delete(IEntity object) throws SQLException {
        Statement s = null;
        try {
            String ime = object.getClass().getSimpleName();
//            String query = "DELETE FROM " + ime + " AS "+ ime.charAt(0) +" " + object.getWhereCondition();
            String query = "DELETE FROM " + ime +" "+ object.getWhereCondition();

            s = connection.createStatement();
            s.executeUpdate(query);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    @Override
    public List<IEntity> getAll(IEntity t) throws Exception {
        Statement s = null;
        ResultSet rs = null;
        try {
            String query = t.getSelectQuery();//iz klase
            System.out.println(query);
            List<IEntity> list = new ArrayList<>();
            s = connection.createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                IEntity domainObject = t.getDomainObjectFromRS(rs);//iz klase
                list.add(domainObject);
            }
            System.out.println(list);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (s != null) {
                s.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public List<IEntity> getAllFilter(IEntity t, IEntity filter) throws Exception {
        Statement s = null;
        ResultSet rs = null;
        try {
//            String query = t.getSelectQuery() + " WHERE " + filter.getWhereCondition();
            String query = t.getSelectQuery() + " " + filter.getWhereCondition();
            System.out.println(query);
            List<IEntity> list = new ArrayList<>();
            s = connection.createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                IEntity domainObject = t.getDomainObjectFromRS(rs);
                list.add(domainObject);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (s != null) {
                s.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}
