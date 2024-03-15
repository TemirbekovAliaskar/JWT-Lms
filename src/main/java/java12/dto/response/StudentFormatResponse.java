package java12.dto.response;

import java12.entity.enums.StudyFormat;

public record StudentFormatResponse(Long id, String fullName, String lastName, String phoneNumber,StudyFormat studyFormat) {
}
