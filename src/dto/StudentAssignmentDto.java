/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author eon_A
 */
public class StudentAssignmentDto {
    
    private String studentFirstName;
    private String studentLastName;
    private String assignmentDescr;
    private int oralMark;
    private int totalMark;
    private LocalDate SubDate;
    private String Stream;
    private String type;

    public StudentAssignmentDto(String studentFirstName, String studentLastName, String assignmentDescr, int oralMark, int totalMark, LocalDate SubDate, String Stream, String type) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.assignmentDescr = assignmentDescr;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.SubDate = SubDate;
        this.Stream = Stream;
        this.type = type;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public String getAssignmentDescr() {
        return assignmentDescr;
    }

    public int getOralMark() {
        return oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public LocalDate getSubDate() {
        return SubDate;
    }

    public String getStream() {
        return Stream;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Assignment Description" + assignmentDescr + ", Oral Mark=" + oralMark + ", Total Mark=" + totalMark + ", Sub Date=" + SubDate + ", Stream=" + Stream + ", Type=" + type; //Eventhough First Name and Last Name exist in my dtο, I didn't want them to show.
    }
    
    
    
    
    
    
}
