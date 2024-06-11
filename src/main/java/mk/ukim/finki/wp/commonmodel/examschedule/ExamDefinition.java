package mk.ukim.finki.wp.commonmodel.examschedule;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.wp.commonmodel.base.TrackedHistoryEntity;
import mk.ukim.finki.wp.commonmodel.teachingallocation.JoinedSubject;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ExamDefinition extends TrackedHistoryEntity {

    // WP-JUNE-LAB
    @Id
    private String id;

    @ManyToOne
    JoinedSubject subject;

    @Enumerated(EnumType.STRING)
    private ExamSession examSession;

    private Long durationMinutes;

    @Enumerated(EnumType.STRING)
    private ExamType type;

    private String note;

}
