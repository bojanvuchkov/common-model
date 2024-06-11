package mk.ukim.finki.wp.commonmodel.equivalence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.Student;
import mk.ukim.finki.wp.commonmodel.base.Subject;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentGrades {

    @Id
    public String id;

    @ManyToOne
    public Student student;

    @ManyToOne
    public Subject subject;

    public LocalDateTime gradeDate;

    public Short grade;

    public String ectsGrade;

    public StudentGrades(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
        this.id = String.format("%s_%s", student.getIndex(), subject.getId());
    }
}
