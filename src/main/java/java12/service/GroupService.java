package java12.service;

import java12.dto.request.GroupRequest;
import java12.dto.response.GroupResponse;
import java12.dto.response.HTTPResponse;

import java.util.List;

public interface GroupService {

    HTTPResponse save(GroupRequest groupRequest);

    List<GroupResponse> findAll();

    HTTPResponse deleteById(Long groupId);

    HTTPResponse update(Long groupId, GroupRequest groupRequest);

     List<String> countById(Long groupId);

    GroupResponse findById(Long groupId);
}
