package java12.api;

import java12.dto.request.StudentRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.StudentFormatResponse;
import java12.dto.response.StudentResponse;
import java12.entity.enums.StudyFormat;
import java12.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentAPI {
    private final StudentService studentService;

    @Secured("ADMIN")
    @PostMapping
    public HTTPResponse save(@RequestBody StudentRequest studentRequest){
        return studentService.save(studentRequest);
    }


    @Secured({"ADMIN"})
    @PostMapping("{groupId}/{studentId}")
    public HTTPResponse asSign(@PathVariable Long groupId,@PathVariable Long studentId){
        return studentService.asSign(groupId,studentId);
    }

    @Secured({"ADMIN"})
    @PostMapping ("delete/{studentId}")
    public HTTPResponse delete(@PathVariable Long studentId){
        return studentService.deleteById(studentId);
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @PostMapping("/update/{studentId}")
    public HTTPResponse update(@PathVariable Long studentId,@RequestBody StudentRequest studentRequest){
        return studentService.update(studentId,studentRequest);
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping("/all")
    public List<StudentResponse> findAll(){
        return studentService.findAll();
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping("/find/{studentId}")
    public StudentResponse find(@PathVariable Long studentId){
       return studentService.findById(studentId);
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping ("/sortFormat")
    public List<StudentFormatResponse> sortByFormat(@RequestParam StudyFormat studyFormat){
        return studentService.sortByFormat(studyFormat);
    }


    @Secured({"ADMIN"})
    @PostMapping("/block/{studentId}")
    public HTTPResponse blockStudent(@PathVariable Long studentId){
        return studentService.blockStudent(studentId);
    }
}
