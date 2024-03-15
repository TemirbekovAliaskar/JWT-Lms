package java12.service;

import java12.dto.request.UserRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.UserResponse;

public interface UserService {
    HTTPResponse signRegister(UserRequest userRequest);

    UserResponse signIn(UserResponse userResponse);

//    HTTPResponse asSign(Long userId, Long companyId);
}
