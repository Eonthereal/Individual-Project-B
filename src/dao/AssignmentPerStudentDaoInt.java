/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDate;

/**
 *
 * @author eon_A
 */
public interface AssignmentPerStudentDaoInt {
     public void insertAssignmentPerStudent (int aid , int sid, LocalDate subDate);
    
    
}
