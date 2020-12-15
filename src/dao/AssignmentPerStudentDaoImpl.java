/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbUtils;

/**
 *
 * @author eon_A
 */
public class AssignmentPerStudentDaoImpl implements AssignmentPerStudentDaoInt{
private Connection con = null;

    @Override
    public void insertAssignmentPerStudent(int aid, int sid, LocalDate subDate) {
        String sql = "INSERT INTO assignmentperstudent Values (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, aid);
            ps.setInt(2, sid);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            Date date = Date.valueOf(subDate);
            ps.setDate(5, date);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentPerStudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentPerStudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
    }
    
    
    
}
