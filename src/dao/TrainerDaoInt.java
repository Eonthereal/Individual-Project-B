/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Trainer;
import java.util.List;

/**
 *
 * @author eon_A
 */
public interface TrainerDaoInt {
    
    public List<Trainer> getAllTrainers();
    
    public List<Trainer> getTrainerByCourseId(int tid);
    
    public int maxTrainerId();
    
    public void insertTrainer(Trainer t);
    
    public Trainer getTrainerByID(int tid);
    
    public void changeTrainerCourse(int cid, int tid, String Subject );
    
}
