/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model1.Assignment;
import utils1.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eon_A
 */
public class AssignmentDaoImpl implements AssignmentDaoInt{
private Connection con = null;

    @Override
    public List<Assignment> getAllAssignments() {
        String sql = "SELECT * FROM assignment";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Assignment> assignmentList = new ArrayList();
    try {
        con=DbUtils.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            Assignment assignmentTemp= new Assignment (rs.getInt("AssignmentID"), rs.getString("Title"), rs.getString("Description"));
            assignmentList.add(assignmentTemp);
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    return assignmentList;
    }
    
   
    
}
