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
       Course result = null;
       try {
          con=DbUtils.getConnection();
          ps = con.prepareStatement(sql);
          ps.setInt(1, cid);
          rs = ps.executeQuery();
          if (rs.next()){
              result = new Course();
              result.setCourseID(rs.getInt("CourseID"));
              result.setTitle(rs.getString("Title"));
              result.setStream(rs.getString("Stream"));
              result.setType(rs.getString("Type"));
              result.setStartDate(rs.getObject("StartDate", Date.class).toLocalDate());
              result.setEndDate(rs.getObject("EndDate", Date.class).toLocalDate());              
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
       return result;
    }
    
}
