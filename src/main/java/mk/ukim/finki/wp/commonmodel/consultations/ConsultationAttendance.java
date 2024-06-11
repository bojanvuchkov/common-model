package mk.ukim.finki.wp.commonmodel.consultations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.wp.commonmodel.base.Student;

@Entity
@Table(name = "consultation_attendance")
@Getter
@Setter
public class ConsultationAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student attendee;

    @ManyToOne
    private Consultation consultation;

    @Column(length = 1000)
    private String comment;

    public ConsultationAttendance(Student attendee, Consultation consultation, String comment) {
        this.attendee = attendee;
        this.consultation = consultation;
        this.comment = comment;
    }

    public ConsultationAttendance() {

    }
}
