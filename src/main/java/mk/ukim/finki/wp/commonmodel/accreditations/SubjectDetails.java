package mk.ukim.finki.wp.commonmodel.accreditations;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.Subject;
import mk.ukim.finki.wp.commonmodel.base.TrackedHistoryEntity;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubjectDetails extends TrackedHistoryEntity {

    @Id
    @Column(name = "id")
    private String id;

    private String copyOfSubjectDetailsId;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Subject subject;

    private Boolean placeholder;

    private String nameEn;

    private Short defaultSemester;

    private Float credits;

    @Enumerated(EnumType.STRING)
    private StudyCycle cycle;


    private String language;


    @Column(length = 8000)
    private String learningMethods;


    @Column(length = 8000)
    private String goalsDescription;

    @Column(length = 8000)
    private String content;

    @Column(length = 8000)
    private String goalsDescriptionEn;

    @Column(length = 8000)
    private String contentEn;

    @Column(length = 4000)
    private String qualityControl;


    @ManyToOne(fetch = FetchType.LAZY)
    private Accreditation accreditation;

    @Embedded
    private SubjectObligationDuration obligationDuration;

    @Embedded
    private SubjectDependencies dependencies;

    @Embedded
    private SubjectGrading grading;

    @Embedded
    private SubjectBibliography bibliography;

    @ElementCollection
    private List<String> notes;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubjectDetails professor = (SubjectDetails) o;
        return getId() != null && Objects.equals(getId(), professor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
