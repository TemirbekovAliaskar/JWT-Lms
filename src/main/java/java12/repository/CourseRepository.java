package java12.repository;

import java12.dto.response.CourseResponse;
import java12.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("select new java12.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart, c.description) from Course c where c.company.id = :companyId")
    List<CourseResponse> findAllByID(Long companyId);


    @Query("select c from Course c where c.id in (:courseIds)")
    List<Course> findCoursesWithIds(List<Long> courseIds);

    @Query("select new java12.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart desc ")
    List<CourseResponse> sort(Long companyId);
    @Query("select new java12.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart  ")
    List<CourseResponse> sortD(Long companyId);

    @Query("select new java12.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c where c.id = :courseId")
    CourseResponse findByIdCourse(Long courseId);


}
