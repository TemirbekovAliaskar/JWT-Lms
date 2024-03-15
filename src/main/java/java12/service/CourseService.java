package java12.service;

import java12.dto.request.CourseRequest;
import java12.dto.response.CourseResponse;
import java12.dto.response.HTTPResponse;

import java.util.List;

public interface CourseService {
    HTTPResponse save(Long companyId, CourseRequest courseRequest);

    HTTPResponse updatedById(Long companyId, Long courseId,CourseRequest courseRequest);

    List<CourseResponse> findAllCourse(Long companyId,String ascOrDesc);

    HTTPResponse deleteById(Long courseId);

    CourseResponse findById(Long courseId);
}
