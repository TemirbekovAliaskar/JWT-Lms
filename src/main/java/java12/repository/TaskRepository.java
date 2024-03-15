package java12.repository;

import java12.dto.response.TaskResponse;
import java12.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
    select new java12.dto.response.TaskResponse(l.id,l.taskName,l.taskText,l.deadLine)
    from Task l join l.lesson ls where ls.id =:lessonId
    """)
    List<TaskResponse> findAllLessonId(Long lessonId);

    @Query("""
    select new java12.dto.response.TaskResponse(l.id,l.taskName,l.taskText,l.deadLine)
    from Task l where l.id =:taskId
    """)
    TaskResponse findByTaskId(Long taskId);
}