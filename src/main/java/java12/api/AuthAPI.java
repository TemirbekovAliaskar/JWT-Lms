package java12.api;


import java12.dto.request.UserRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.UserResponse;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {

    private final UserService userService;

    @PostMapping("/register")
    public HTTPResponse signUp(@RequestBody UserRequest userRequest){
        return userService.signRegister(userRequest);
    }

    @GetMapping
    public UserResponse signIn(@RequestBody UserResponse userResponse){
        return userService.signIn(userResponse);
    }

//
//    @PostMapping ("/{userId}/{companyId}")
//    public HTTPResponse asSignUserToCompany(@PathVariable Long userId,@PathVariable Long companyId){
//        return userService.asSign(userId,companyId);
//    }
}
