package mk.ukim.finki.wp.commonmodel.examschedule;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.accreditations.StudyCycle;
import mk.ukim.finki.wp.commonmodel.base.Semester;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class YearExamSession {

    // 2022-23-JUNE
    @Id
    private String name;

    @Enumerated(EnumType.STRING)
    private ExamSession session;

    // 2022-23
    private String year;

    private LocalDate sessionStart;

    private LocalDate sessionEnd;

    private LocalDate enrollmentStartDate;

    private LocalDate enrollmentEndDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<StudyCycle> cycle;


    public YearExamSession(ExamSession session, String year) {
        this.name = String.format("%s-%s", year, session.name());
        this.session = session;
        this.year = year;
    }
}
