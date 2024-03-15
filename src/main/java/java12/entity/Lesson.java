package java12.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson-seq", sequenceName = "lesson-gen", allocationSize = 1)
    private Long id;
    private String lessonName;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Course course;
    @OneToMany(mappedBy = "lesson",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Task> tasks = new ArrayList<>();
}
