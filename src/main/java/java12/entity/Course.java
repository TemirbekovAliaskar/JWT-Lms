package java12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course-seq", sequenceName = "course-gen", allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    @ManyToMany(mappedBy = "courses")
    private List<Group> groups = new ArrayList<>();
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Company company;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private Instructor instructor;
    @OneToMany(mappedBy = "course",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Lesson> lessons = new ArrayList<>();

    @PrePersist
    private void prePersist(){
        this.dateOfStart= LocalDate.now();
    }

    public void addGroup(Group group) {
        if (this.groups == null) this.groups = new ArrayList<>();
        this.groups.add(group);
    }
//




}
