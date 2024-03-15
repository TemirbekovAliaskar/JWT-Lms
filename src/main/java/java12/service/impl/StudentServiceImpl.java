package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.StudentRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.StudentFormatResponse;
import java12.dto.response.StudentResponse;
import java12.entity.Group;
import java12.entity.Student;
import java12.entity.User;
import java12.entity.enums.Role;
import java12.entity.enums.StudyFormat;
import java12.jwt.JwtService;
import java12.repository.GroupRepository;
import java12.repository.StudentRepository;
import java12.repository.UserRepository;
import java12.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    @Override @Transactional
    public HTTPResponse save(StudentRequest studentRequest) {
        try {

            User user = saveUserInfo(studentRequest.fullName(), studentRequest.lastName(), studentRequest.email(), studentRequest.password());
            Student student = new Student();
            student.setFullName(studentRequest.fullName());
            student.setLastName(studentRequest.lastName());
            student.setPhoneNumber(studentRequest.phoneNumber());
            student.setStudyFormat(studentRequest.studyFormat());
            student.setUser(user);
            studentRepository.save(student);
            String token = jwtService.createToken(user);
            return HTTPResponse.builder()
                    .token(token)
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully saved students !")
                    .build();
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    private User saveUserInfo(String fullName, String lastName, String email, String password) {
        User user = User.builder()
                .name(fullName + " " +lastName)
                .email(email)
                .role(Role.STUDENT)
                .build();

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;

    }

    @Override @Transactional
    public HTTPResponse asSign(Long groupId, Long studentId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Not found this id !" + groupId));
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            student.setGroup(group);
            group.addStudents(student);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully as signed students !")
                    .build();
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public HTTPResponse deleteById(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId      // Устанавливаем пароль для пользователя

).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            studentRepository.delete(student);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted students !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override @Transactional
    public HTTPResponse update(Long studentId, StudentRequest studentRequest) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            student.setFullName(studentRequest.fullName());
            student.setLastName(studentRequest.lastName());
            student.setPhoneNumber(studentRequest.phoneNumber());
            student.setStudyFormat(studentRequest.studyFormat());
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully updated students !")
                    .build();
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<StudentResponse> findAll() {
        try {
            return studentRepository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<StudentFormatResponse> sortByFormat(StudyFormat studyFormat) {
        return studentRepository.sortByFormat(studyFormat);
    }

    @Override @Transactional
    public HTTPResponse blockStudent(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            boolean block = student.isBlock();
            if (block) {
                student.setBlock(false);
                return HTTPResponse.builder()
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Successfully blocked students !")
                        .build();
            } else {
                student.setBlock(true);
                return HTTPResponse.builder()
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Successfully unblocked students !")
                        .build();
            }
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public StudentResponse findById(Long studentId) {
        try {
            return studentRepository.findByIdStudents(studentId);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage()+ studentId);
        }
    }

}
