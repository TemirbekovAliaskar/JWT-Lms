package java12.dto.request;

import java12.entity.enums.Role;
import java12.entity.enums.StudyFormat;

public record StudentRequest(String fullName, String lastName, String email, String password, String passwordConfirm, Role role,String phoneNumber, StudyFormat studyFormat) {
}
