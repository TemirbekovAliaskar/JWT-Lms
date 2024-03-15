package java12.entity;
import jakarta.persistence.*;
import java12.entity.enums.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student-seq", sequenceName = "student-gen", allocationSize = 1)
    private Long id;
    private String fullName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    private boolean isBlock = true;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Group group;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private User user;
}
