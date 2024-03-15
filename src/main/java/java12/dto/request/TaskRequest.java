package java12.dto.request;

import java.time.ZonedDateTime;

public record TaskRequest(String taskName,String taskText,ZonedDateTime deadLine) {
}
