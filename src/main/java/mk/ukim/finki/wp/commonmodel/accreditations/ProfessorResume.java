package mk.ukim.finki.wp.commonmodel.accreditations;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.wp.commonmodel.base.Professor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfessorResume {

    @Id
    @OneToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Professor professor;

    @Column(length = 10_000)
    private String biography;

    private byte[] image;
}
