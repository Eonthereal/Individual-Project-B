/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Trainer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbUtils;

/**
 *
 * @author eon_A
 */
public class TrainerDaoImpl implements TrainerDaoInt {

    private Connection con = null;

    @Override
    public List<Trainer> getAllTrainers() {

        String sql = "SELECT * FROM trainer";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Trainer> trainerList = new ArrayList();
        CourseDaoImpl cdi = new CourseDaoImpl();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Trainer trainerTemp = new Trainer();
                trainerTemp.setTrainerID(rs.getInt("TrainerID"));
                trainerTemp.setFirstName(rs.getString("FirstName"));
                trainerTemp.setLastName(rs.getString("LastName"));
                trainerTemp.setSubject(rs.getString("Subject"));
                trainerTemp.setCourse(cdi.getCourseById(rs.getInt("CourseId")));
                trainerList.add(trainerTemp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return trainerList;

    }

    @Override
    public List<Trainer> getTrainerByCourseId(int tid) {
        String sql = "SELECT * FROM trainer INNER JOIN course ON trainer.courseid = course.courseid "
                + "WHERE trainer.courseid=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Trainer> trainerList = new ArrayList();
        CourseDaoImpl cdi = new CourseDaoImpl();
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, tid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Trainer trainerTemp = new Trainer();
                trainerTemp.setTrainerID(rs.getInt("TrainerID"));
                trainerTemp.setFirstName(rs.getString("FirstName"));
                trainerTemp.setLastName(rs.getString("LastName"));
                trainerTemp.setSubject(rs.getString("Subject"));
                trainerTemp.setCourse(cdi.getCourseById(rs.getInt("CourseId")));
                trainerList.add(trainerTemp);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return trainerList;

    }

    @Override
    public int maxTrainerId() {
        String sql = "SELECT MAX(trainer.trainerid) from trainer";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxID = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt("MAX(trainer.trainerid)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return maxID;
    }

    @Override
    public void insertTrainer(Trainer t) {
        String sql = "INSERT INTO trainer VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, t.getTrainerID());
            ps.setString(2, t.getFirstName());
            ps.setString(3, t.getLastName());
            ps.setString(4, t.getSubject());
            ps.setInt(5, t.getCourse().getCourseID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Trainer getTrainerByID(int tid){
     String sql = "select *  from trainer where trainerid=?";
       PreparedStatement ps = null;
       ResultSet rs = null;
       Trainer trainer = null;
       CourseDaoImpl cdi = new CourseDaoImpl();
       try {
          con=DbUtils.getConnection();
          ps = con.prepareStatement(sql);
          ps.setInt(1, tid);
          rs = ps.executeQuery();
          if (rs.next()){
              trainer = new Trainer();
              trainer.setTrainerID(rs.getInt("TrainerID"));
              trainer.setFirstName(rs.getString("FirstName"));
              trainer.setLastName(rs.getString("LastName"));
              trainer.setSubject(rs.getString("Subject"));
              trainer.setCourse(cdi.getCourseById(rs.getInt("CourseId")));                  
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
       return trainer;
    }
    @Override
    public void changeTrainerCourse(int cid, int tid, String Subject ){
    String sql = "update trainer set courseid=?, trainer.subject=? where trainerid=?";
    PreparedStatement ps = null;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setString(2, Subject);
            ps.setInt(3, tid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    

}
