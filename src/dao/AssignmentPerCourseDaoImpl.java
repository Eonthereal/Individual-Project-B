/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbUtils;

/**
 *
 * @author eon_A
 */
public class AssignmentPerCourseDaoImpl implements AssignmentPerCourseDaoInt {
private Connection con = null;
    @Override
    public void insertAssignmentPerCourse(int aid , int cid) {
      String sql = "INSERT INTO assignmentpercourse VALUES (?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, aid);
            ps.setInt(2, cid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerCourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentPerCourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
    }
    
    
}
