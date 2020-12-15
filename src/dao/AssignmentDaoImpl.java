/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.StudentAssignmentDto;
import model.Assignment;
import utils.DbUtils;
import java.sql.Connection;
import java.sql.Date;
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
public class AssignmentDaoImpl implements AssignmentDaoInt {

    private Connection con = null;

    @Override
    public List<Assignment> getAllAssignments() {
        String sql = "SELECT * FROM assignment";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Assignment> assignmentList = new ArrayList();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Assignment assignmentTemp = new Assignment(rs.getInt("AssignmentID"), rs.getString("Title"), rs.getString("Description"));
                assignmentList.add(assignmentTemp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return assignmentList;
    }

    @Override
    public List<Assignment> getAssignmentsByCourseId(int cid) {
        String sql = "SELECT assignment.* FROM assignmentpercourse, assignment, course "
                + "WHERE  assignmentpercourse.courseid = course.courseid "
                + "AND assignmentpercourse.AssignmentID = assignment.AssignmentID "
                + "AND course.CourseID=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Assignment> assignmentList = new ArrayList();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Assignment assignmentTemp = new Assignment(rs.getInt("AssignmentID"), rs.getString("Title"), rs.getString("Description"));
                assignmentList.add(assignmentTemp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return assignmentList;
    }

    @Override
    public List<StudentAssignmentDto> getAssignmentsByStudentId(int sid) {
        String sql = "SELECT student.FirstName, student.LastName, assignment.Description, assignmentperstudent.OralMark, assignmentperstudent.TotalMark,assignmentperstudent.SubDateTime, course.Stream, course.Type "
                + "FROM assignmentperstudent, assignment,student , course,assignmentpercourse "
                + "WHERE assignmentperstudent.StudentID = student.StudentID "
                + "AND assignmentperstudent.AssignmentID= assignment.AssignmentID "
                + "AND assignmentpercourse.AssignmentID = assignment.AssignmentID "
                + "AND assignmentpercourse.CourseID = course.CourseID "
                + "AND Student.StudentID=?";
        List<StudentAssignmentDto> studentAssignmentList = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getObject("SubDateTime", Date.class);
                StudentAssignmentDto assignmentTemp = new StudentAssignmentDto(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Description"), rs.getInt("OralMark"), rs.getInt("TotalMark"), date.toLocalDate(), rs.getString("Stream"), rs.getString("Type"));
                studentAssignmentList.add(assignmentTemp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return studentAssignmentList;
    }

    @Override
    public int maxAssignmentId() {
        String sql = "SELECT MAX(assignment.assignmentid) from assignment";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxID = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt("MAX(assignment.assignmentid)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return maxID;

    }

    @Override
    public void insertAssingnment(Assignment a) {
        String sql = "INSERT INTO assignment VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getAssignmentID());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void assignmentDispatcher(){
    
    
    }
    

}
