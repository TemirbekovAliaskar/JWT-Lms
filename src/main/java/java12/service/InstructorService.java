package java12.service;

import java12.dto.request.InstructorRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.InfoInstructorsResponse;
import java12.dto.response.InstructorResponse;

import java.util.List;

public interface InstructorService {
    HTTPResponse save(InstructorRequest instructorRequest);

    HTTPResponse asSign(Long companyId, Long instructorId);

    HTTPResponse update(Long instructorId, InstructorRequest instructorRequest);

    HTTPResponse delete(Long instructorId);

    List<InstructorResponse> findAll(Long companyId);

    Integer countStudent(Long inId);

    HTTPResponse asSignCourse(Long courseId, Long instructorId);

    InfoInstructorsResponse infoInstructor(Long instructorId);
}
