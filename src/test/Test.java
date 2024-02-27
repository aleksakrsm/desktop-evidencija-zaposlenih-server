/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import psproject_v5.connection.ConnectionPool;
import psproject_v5.controller.Controller;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.IEntity;
import psproject_v5.domain.Status;
import psproject_v5.exception.RepositoryException;
import psproject_v5.exception.ValidatorException;
import psproject_v5.repository.db.impl.GenericRepository;

/**
 *
 * @author aleks
 */
public class Test {
    public static void main(String[] args) {
        try {
        String name = "Vanredni profesor";
        Pattern pattern = Pattern.compile("[A-Z][a-z]{2,20}(\s[A-Z]{0,1}[a-z]{1,20})*");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + name);
        } else {
            throw new RepositoryException("Unos nije ispravan!!! [A-Z][a-z]{2,20}(\s[A-Z]{0,1}[a-z]{1,20})*");
        }
            
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
