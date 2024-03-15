package java12.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company-seq", sequenceName = "company-gen", allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;


    @ManyToMany(mappedBy = "companies", cascade = CascadeType.DETACH)
    private List<Instructor> instructors = new ArrayList<>();
    @OneToMany(mappedBy = "company",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Course> courses;



    public void addCourse(Course course){
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public void addInstructor(Instructor instructor){
        if (this.instructors == null) this.instructors = new ArrayList<>();
        this.instructors.add(instructor);
    }


    }


