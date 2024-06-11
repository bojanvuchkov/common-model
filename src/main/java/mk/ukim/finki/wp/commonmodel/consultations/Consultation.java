package mk.ukim.finki.wp.commonmodel.consultations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.ukim.finki.wp.commonmodel.base.Professor;
import mk.ukim.finki.wp.commonmodel.base.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Consultation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Room room;

    @Enumerated(EnumType.STRING)
    private ConsultationType type;

    // This is only used for the one time consultations
    private LocalDate oneTimeDate;

    @Enumerated(EnumType.STRING)
    private DayOfWeek weeklyDayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    @ElementCollection
    private List<LocalDate> canceledDates;

    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;
}
