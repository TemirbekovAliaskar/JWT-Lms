package java12.dto.response;

import java12.entity.enums.Specialization;

public record InstructorResponse(Long id,String firstName, String lastName, String phoneNumber, Specialization specialization) {
}
