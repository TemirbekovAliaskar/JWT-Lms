package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.CourseRequest;
import java12.dto.response.CourseResponse;
import java12.dto.response.HTTPResponse;
import java12.entity.Company;
import java12.entity.Course;
import java12.repository.CompanyRepository;
import java12.repository.CourseRepository;
import java12.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public HTTPResponse save(Long companyId, CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company id this" + companyId + "not found!"));
            Course course = new Course();
            course.setCompany(company);
            company.addCourse(course);
            course.setCourseName(courseRequest.courseName());
            course.setDescription(courseRequest.description());
            course.setDateOfStart(courseRequest.dateOfStart());
            courseRepository.saveAndFlush(course);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully save Course !")
                    .build();
        } catch (Exception e) {
           return HTTPResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }


    @Override @Transactional
    public HTTPResponse updatedById(Long companyId, Long courseId,CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company id this" + companyId + "not found!"));
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course this id " + courseId + "not found !"));
            course.setCompany(company);
            course.setCourseName(courseRequest.courseName());
            course.setDescription(courseRequest.description());
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully updated Course !")
                    .build();
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<CourseResponse> findAllCourse(Long companyId,String ascOrDesc) {
        if (ascOrDesc.contains("asc")) {
            return courseRepository.sortD(companyId);
        } else  {
           return courseRepository.sort(companyId);
        }
    }

    @Override
    public HTTPResponse deleteById(Long courseId) {
        try {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course this id " + courseId + "not found !"));
        courseRepository.delete(course);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted course")
                .build();
        }
        catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CourseResponse findById(Long courseId) {
        try {
            return courseRepository.findByIdCourse(courseId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }return null;
    }
}
