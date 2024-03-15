package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.LessonRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.LessonResponse;
import java12.entity.Course;
import java12.entity.Lesson;
import java12.repository.CourseRepository;
import java12.repository.LessonRepository;
import java12.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    @Override
    public HTTPResponse save(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Not found" + courseId));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.lessonName());
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved Lesson !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse update(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Not found !"));
        lesson.setLessonName(lessonRequest.lessonName());
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated Lesson !")
                .build();
    }

    @Override
    public HTTPResponse deleteById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Not found !"));
        lessonRepository.delete(lesson);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted Lesson !")
                .build();
    }

    @Override
    public List <LessonResponse> findAll(Long courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }

    @Override
    public LessonResponse findById(Long lessonId) {
        try {
            return lessonRepository.findByIdLesson(lessonId);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Lesson id"+lessonId+e.getMessage());
        }
    }
}
