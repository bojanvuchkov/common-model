package mk.ukim.finki.wp.commonmodel.accreditations;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectBibliography {

    @ElementCollection
    private List<Book> books;

    @ElementCollection
    private List<Book> electiveBooks;
}
