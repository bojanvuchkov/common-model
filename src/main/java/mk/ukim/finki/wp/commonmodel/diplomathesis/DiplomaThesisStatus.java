package mk.ukim.finki.wp.commonmodel.diplomathesis;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiplomaThesisStatus {
    PROFESSOR_THESIS_REGISTRATION(1),
    STUDENT_APPROVAL(2),
    ADMINISTRATION_VALIDATION(3),
    VICE_DEAN_APPROVAL(4),
    PENDING_TEXT(5),
    MENTOR_TEXT_APPROVAL(6),
    MEMBERS_TEXT_APPROVAL(7),
    ADMINISTRATION_PRESENTATION_VALIDATION(8),
    MENTOR_PRESENTATION_SCHEDULING(9),
    PENDING_GRADE(10),
    MEMBERS_GRADE_APPROVAL(11),
    ARCHIVE(12),
    CANCELED(30);

    private double order;
}
