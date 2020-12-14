/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Course;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbUtils;

/**
 *
 * @author eon_A
 */
public class CourseDaoImpl implements CourseDaoInt {
private Connection con = null;

    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM course";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList();
    try {
        con=DbUtils.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
           Date startDate=rs.getObject("StartDate", Date.class);
           Date endDate= rs.getObject("EndDate", Date.class);
            Course courseTemp= new Course (rs.getInt("CourseID"), rs.getString("Title"), rs.getString("Stream"), rs.getString("Type"), startDate.toLocalDate(), endDate.toLocalDate());
            courseList.add(courseTemp);
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    return courseList;
        
        
            
    }

    @Override
    public Course getCourseById(int cid) {
       String sql = "select *  from course where courseid=?";
       PreparedStatement ps = null;
       ResultSet rs = null;
       Course course = null;
       try {
          con=DbUtils.getConnection();
          ps = con.prepareStatement(sql);
          ps.setInt(1, cid);
          rs = ps.executeQuery();
          if (rs.next()){
              course = new Course();
              course.setCourseID(rs.getInt("CourseID"));
              course.setTitle(rs.getString("Title"));
              course.setStream(rs.getString("Stream"));
              course.setType(rs.getString("Type"));
              course.setStartDate(rs.getObject("StartDate", Date.class).toLocalDate());
              course.setEndDate(rs.getObject("EndDate", Date.class).toLocalDate());              
          }
           } catch (SQLException ex) {
        Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }    
       
       }
       return course;
    }

    @Override
    public int maxCourseId() {
        String sql = "SELECT MAX(course.courseid) from course";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxID = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt("MAX(course.courseid)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return maxID;
    }

    @Override
    public void insertCourse(Course c) {
        String sql = "INSERT INTO course VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, c.getCourseID());
            ps.setString(2, c.getTitle());
            ps.setString(3, c.getStream());
            ps.setString(4, c.getType());
            Date date = Date.valueOf(c.getStartDate());
            ps.setDate(5, date);
            System.out.println("check before");
            Date date2 = Date.valueOf(c.getEndDate());
            ps.setDate(6, date2);
            System.out.println("check after");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}
