package java12.api;

import java12.dto.request.GroupRequest;
import java12.dto.response.GroupResponse;
import java12.dto.response.HTTPResponse;
import java12.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupAPI {

    private final GroupService groupService;

    @Secured({"ADMIN"})
    @PostMapping("/save")
    public HTTPResponse save(@RequestBody GroupRequest groupRequest){
        return groupService.save(groupRequest);
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping("/all")
    public List <GroupResponse> findAll(){
        return groupService.findAll();
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping("/find/{groupId}")
    public GroupResponse find(@PathVariable Long groupId){
        return groupService.findById(groupId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/delete/{groupId}")
    public HTTPResponse delete(@PathVariable Long groupId){
        return groupService.deleteById(groupId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/update/{groupId}")
    public HTTPResponse update(@PathVariable Long groupId,@RequestBody GroupRequest groupRequest){
        return groupService.update(groupId,groupRequest);
    }

    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping("count/{groupId}")
    public List<String> count(@PathVariable Long groupId){
        return groupService.countById(groupId);
    }

}
