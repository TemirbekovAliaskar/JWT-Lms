package java12.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group-seq", sequenceName = "group-gen", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "group",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Student> students = new ArrayList<>();

    public void addCourse(Course course) {
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public void addStudents(Student student){
        if (this.students == null) this.students = new ArrayList<>();
        this.students.add(student);
    }
}
