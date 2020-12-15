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

    @Override
    public List<Student> getStudentsMoreThanOneCourses() {
        String sql = "SELECT student.* "
                + "FROM courseperstudent "
                + "INNER JOIN student "
                + "ON student.StudentID = courseperstudent.studentid "
                + "GROUP BY courseperstudent.studentid "
                + "HAVING count(courseperstudent.courseid)>1";
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
    public int maxStudentId() {
        String sql = "SELECT MAX(student.studentid) from student";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxID = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt("MAX(student.studentid)");
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

        return maxID;
    }

    @Override
    public void insertStudent(Student s) {
        String sql = "INSERT INTO student VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, s.getStundentID());
            ps.setString(2, s.getFirstName());
            ps.setString(3, s.getLastName());
            Date date = Date.valueOf(s.getDateOfBirth());
            ps.setDate(4, date);
            ps.setInt(5, s.getTuitionFees());
            ps.executeUpdate();
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

    }

    @Override
    public List<Student> printListOfStudentsWithoutCourse() {
        String sql = "SELECT * FROM student WHERE student.studentid "
                + "NOT IN (SELECT courseperstudent.studentid "
                + "FROM courseperstudent)";
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
    public void addExistingStudentToCourse(int cid, int sid) {
      String sql = "INSERT INTO courseperstudent VALUES (?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, sid);
            ps.executeUpdate();
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
        
    }    

}
