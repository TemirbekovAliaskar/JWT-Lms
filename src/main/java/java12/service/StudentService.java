package java12.service;

import java12.dto.request.StudentRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.StudentFormatResponse;
import java12.dto.response.StudentResponse;
import java12.entity.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    HTTPResponse save(StudentRequest studentRequest);

    HTTPResponse asSign(Long groupId, Long studentId);

    HTTPResponse deleteById(Long studentId);

    HTTPResponse update(Long studentId, StudentRequest studentRequest);

    List<StudentResponse> findAll();

    List<StudentFormatResponse> sortByFormat(StudyFormat studyFormat);

    HTTPResponse blockStudent(Long studentId);

    StudentResponse findById(Long studentId);
}
