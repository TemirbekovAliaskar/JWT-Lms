package java12.service;

import java12.dto.request.LessonRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {
    HTTPResponse save(Long courseId, LessonRequest lessonRequest);

    HTTPResponse update(Long lessonId, LessonRequest lessonRequest);

    HTTPResponse deleteById(Long lessonId);

    List <LessonResponse> findAll(Long courseId);

    LessonResponse findById(Long lessonId);
}
