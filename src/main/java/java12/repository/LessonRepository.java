package java12.repository;

import java12.dto.response.LessonResponse;
import java12.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {


    @Query("""
select new java12.dto.response.LessonResponse(l.id,l.lessonName)
from Lesson l join l.course ls where ls.id =:courseId
""")
   List <LessonResponse> findAllByCourseId(Long courseId);

    @Query("select new java12.dto.response.LessonResponse(l.id,l.lessonName)\n" +
            "from Lesson l where l.id =:lessonId")
    LessonResponse findByIdLesson(Long lessonId);
}