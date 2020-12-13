/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Student;
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
public class StudentDaoImpl implements StudentDaoInt {

    private Connection con = null;

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getObject("DateOfBirth", Date.class); //I'll use date.toLocalDate() inside the constructor
                Student studentTemp = new Student(rs.getInt("StudentID"), rs.getString("FirstName"), rs.getString("LastName"), date.toLocalDate(), rs.getInt("TuitionFees"));
                studentList.add(studentTemp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return studentList;
    }

    @Override
    public List<Student> getStudentsByCourseId(int stid) {
        String sql = "SELECT student.* "
                + "FROM courseperstudent, student, course WHERE courseperstudent.courseid = course.courseid "
                + "AND courseperstudent.studentid = student.studentid "
                + "AND course.CourseID=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getObject("DateOfBirth", Date.class);
                Student studentTemp = new Student(rs.getInt("StudentID"), rs.getString("FirstName"), rs.getString("LastName"), date.toLocalDate(), rs.getInt("TuitionFees"));
                studentList.add(studentTemp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return studentList;

    }

}
