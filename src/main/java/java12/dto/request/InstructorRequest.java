package java12.dto.request;

import java12.entity.enums.Role;
import java12.entity.enums.Specialization;

public record InstructorRequest(String firstName, String lastName, String email, String password, String passwordConfirm, Role role,String phoneNumber, Specialization specialization) {
}
