package java12.api;

import java12.dto.request.TaskRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.TaskResponse;
import java12.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskAPI {
    private final TaskService taskService;

    @Secured("INSTRUCTOR")
    @PostMapping("/saved/{lessonId}")
    public HTTPResponse save(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest){
        return taskService.saveToLesson(lessonId,taskRequest);
    }

    @Secured("INSTRUCTOR")
    @PostMapping("/update/{taskId}")
    public HTTPResponse update(@PathVariable Long taskId,@RequestBody TaskRequest taskRequest){
        return taskService.update(taskId,taskRequest);
    }

    @Secured("INSTRUCTOR")
    @PostMapping("delete/{taskId}")
    public HTTPResponse delete(@PathVariable Long taskId){
        return taskService.delete(taskId);
    }

    @Secured({"INSTRUCTOR","ADMIN","STUDENT"})
    @GetMapping("all/{lessonId}")
    public List<TaskResponse> findAll(@PathVariable Long lessonId){
        return taskService.findAll(lessonId);
    }
    @Secured({"STUDENT","INSTRUCTOR"})
    @GetMapping("find/{taskId}")
    public TaskResponse findById(@PathVariable Long taskId){
        return taskService.findById(taskId);
    }
}
