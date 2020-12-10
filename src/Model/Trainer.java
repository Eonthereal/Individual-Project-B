/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author eon_A
 */
public class Trainer {
    private int trainerID;
    private String firstName;
    private String LastName;
    private String Subject;
    private Course course; // or CourseID TBD...

    public Trainer() {
    }

    public Trainer(int trainerID, String firstName, String LastName, String Subject, Course course) {
        this.trainerID = trainerID;
        this.firstName = firstName;
        this.LastName = LastName;
        this.Subject = Subject;
        this.course = course;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Trainer{" + "trainerID=" + trainerID + ", firstName=" + firstName + ", LastName=" + LastName + ", Subject=" + Subject + ", course=" + course + '}';
    }
    
    
    
    
}
