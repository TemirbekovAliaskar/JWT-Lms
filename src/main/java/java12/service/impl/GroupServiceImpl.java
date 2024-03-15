package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.GroupRequest;
import java12.dto.response.GroupResponse;
import java12.dto.response.HTTPResponse;
import java12.entity.Course;
import java12.entity.Group;
import java12.repository.CourseRepository;
import java12.repository.GroupRepository;
import java12.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public HTTPResponse save(GroupRequest groupRequest) {
        try {

            List<Long> courseIds = groupRequest.coursesIds();
            List<Course> courses = courseRepository.findCoursesWithIds(courseIds);
            Group group = new Group();
            group.setGroupName(groupRequest.groupName());
            group.setDescription(groupRequest.description());
            group.setImageLink(groupRequest.imageLink());

            for (Course course : courses) {
                course.addGroup(group);
                group.addCourse(course);
            }
            groupRepository.save(group);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully created Groups !")
                .build();
    }

    @Override
    public List <GroupResponse> findAll() {
        return groupRepository.findAllGroups();
    }

    @Override
    public HTTPResponse deleteById(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        groupRepository.delete(group);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted Groups !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse update(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        group.setGroupName(groupRequest.groupName());
        group.setDescription(groupRequest.description());
        group.setImageLink(groupRequest.imageLink());

        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated Groups !")
                .build();
    }

    @Override
    public List<String> countById(Long groupId) {
        return groupRepository.countById(groupId);
    }

    @Override
    public GroupResponse findById(Long groupId) {
        return groupRepository.findByIdGroup(groupId);
    }

}
