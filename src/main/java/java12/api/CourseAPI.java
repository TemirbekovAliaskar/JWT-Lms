package java12.api;

import java12.dto.request.CourseRequest;
import java12.dto.response.CourseResponse;
import java12.dto.response.HTTPResponse;
import java12.repository.InstructorRepository;
import java12.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseAPI {
    private final CourseService courseService;
    private final InstructorRepository instructorRepository;

    @Secured({"INSTRUCTOR","ADMIN","STUDENT"})
    @GetMapping("/{companyId}/")
    public List<CourseResponse> findAll(@PathVariable Long companyId,@RequestParam String ascOrDesc){
      return   courseService.findAllCourse(companyId,ascOrDesc);
    }

    @Secured({"INSTRUCTOR","ADMIN","STUDENT"})
    @GetMapping("/find/{courseId}")
    public CourseResponse findById(@PathVariable Long courseId){
        return courseService.findById(courseId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/save/{companyId}")
    public HTTPResponse save(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest){
        return courseService.save(companyId,courseRequest);
    }

    @Secured({"ADMIN"})
    @PostMapping("/update/{companyId}/{postId}")
    public HTTPResponse update(@PathVariable Long companyId, @PathVariable Long postId, @RequestBody CourseRequest courseRequest){
        return courseService.updatedById(companyId,postId,courseRequest);
    }

    @Secured({"ADMIN"})
    @PostMapping("/delete/{courseId}")
    public HTTPResponse delete(@PathVariable Long courseId){
        return courseService.deleteById(courseId);
    }

}
