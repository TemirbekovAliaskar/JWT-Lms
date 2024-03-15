package java12.service.impl;

import java12.dto.request.UserRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.UserResponse;
import java12.entity.User;
import java12.entity.enums.Role;
import java12.jwt.JwtService;
import java12.repository.CompanyRepository;
import java12.repository.InstructorRepository;
import java12.repository.StudentRepository;
import java12.repository.UserRepository;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public HTTPResponse signRegister(UserRequest userRequest) {
        if (userRequest.role().equals(Role.ADMIN)) {
            boolean exist = userRepo.existsByEmail(userRequest.email());
            if (exist) throw new RuntimeException("Email : " + userRequest.email() + "already exist!");
            if (!userRequest.password().equals(userRequest.passwordConfirm()))
                throw new RuntimeException("Invalid password !");

            User user = new User();
            user.setName(userRequest.name());
            user.setEmail(userRequest.email());
            user.setRole(userRequest.role());
            user.setPassword(passwordEncoder.encode(userRequest.password()));
            System.out.println("test");

            String newToken = jwtService.createToken(user);
            userRepo.save(user);


            return HTTPResponse.builder()
                    .token(newToken)
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully ADMIN saved !")
                    .build();

        }
//        if (userRequest.role().equals(Role.INSTRUCTOR)) {
//            boolean existInstructor = instructorRepository.existsByEmail(userRequest.email());
//            if (existInstructor) throw new RuntimeException("Email " + userRequest.email() + "already exist!");
//            if (!userRequest.password().equals(userRequest.passwordConfirm()))
//                throw new RuntimeException("Invalid password !");
//            Instructor instructor = new Instructor();
//            instructor.setName(userRequest.name());
//            instructor.setEmail(userRequest.email());
//            instructor.setSpecialization(userRequest.specialization());
//            instructor.setPhoneNumber(userRequest.phoneNumber());
//            instructor.setRole(userRequest.role());
//            instructor.setPassword(passwordEncoder.encode(userRequest.password()));
//            instructorRepository.save(instructor);
//            return HTTPResponse.builder()
//                    .httpStatus(HttpStatus.OK)
//                    .message("Successfully INSTRUCTOR saved !")
//                    .build();
//        }
//
//        if (userRequest.role().equals(Role.STUDENT)){
//            boolean existStudent = studentRepository.existsByEmail(userRequest.email());
//            if (existStudent) throw new RuntimeException("Email " + userRequest.email()+ "already exist!");
//            if (!userRequest.password().equals(userRequest.passwordConfirm()))
//                throw new RuntimeException("Invalid password !");
//            Student student = new Student();
//            student.setName(userRequest.name());
//            student.setEmail(userRequest.email());
//            student.setPhoneNumber(userRequest.phoneNumber());
//            student.setStudyFormat(userRequest.studyFormat());
//            student.setPassword(passwordEncoder.encode(userRequest.password()));
//            studentRepository.save(student);
//            return HTTPResponse.builder()
//                    .httpStatus(HttpStatus.OK)
//                    .message("Successfully STUDENT saved !")
//                    .build();
//        }
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("ERROR!")
                .build();
    }

    @Override
    public UserResponse signIn(UserResponse userResponse) {
        User user = userRepo.findByEmail(userResponse.email()).orElseThrow(() ->    new NoSuchElementException("User with email: " + userResponse.email()+" not found!"));

        String encodePassword = user.getPassword();
        String password = userResponse.password();

        boolean matches = passwordEncoder.matches(password, encodePassword);

        if (!matches) throw new RuntimeException("Invalid password");
        String token = jwtService.createToken(user);
        return UserResponse.builder()
                .token(token)
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

//    @Override @Transactional
//    public HTTPResponse asSign(Long userId, Long companyId) {
//        User user = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("Not found Id" + userId));
//        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Not found Id" + companyId));
//        user.addCompany(company);
//        company.addUser(user);
//        return HTTPResponse.builder()
//                .httpStatus(HttpStatus.OK)
//                .message("Successfully asSigned !")
//                .build();
//    }
}
