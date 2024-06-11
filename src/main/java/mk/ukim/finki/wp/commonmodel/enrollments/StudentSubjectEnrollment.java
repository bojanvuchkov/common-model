package mk.ukim.finki.wp.commonmodel.enrollments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.Professor;
import mk.ukim.finki.wp.commonmodel.base.Semester;
import mk.ukim.finki.wp.commonmodel.base.Student;
import mk.ukim.finki.wp.commonmodel.base.Subject;
import mk.ukim.finki.wp.commonmodel.teachingallocation.JoinedSubject;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class StudentSubjectEnrollment {

    @Id
    private String id;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private Boolean valid;

    @Column(length = 4000)
    private String invalidNote;

    private Short numEnrollments;

    private String groupName;

    private Long groupId;

    @ManyToOne
    private JoinedSubject joinedSubject;

    @ManyToOne
    private Professor professor;

    private String professors;

    private String assistants;

    public StudentSubjectEnrollment(Semester semester, Student student, Subject subject) {
        this.id = String.format("%s-%s-%s", semester.getCode(), student.getIndex(), subject.getId());
        this.semester = semester;
        this.student = student;
        this.subject = subject;
    }

}
