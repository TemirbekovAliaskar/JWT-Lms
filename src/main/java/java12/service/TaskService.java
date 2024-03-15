package java12.service;

import java12.dto.request.TaskRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    HTTPResponse saveToLesson(Long lessonId, TaskRequest taskRequest);

    HTTPResponse update(Long taskId, TaskRequest taskRequest);

    HTTPResponse delete(Long taskId);

    List<TaskResponse> findAll(Long lessonId);

    TaskResponse findById(Long taskId);
}
