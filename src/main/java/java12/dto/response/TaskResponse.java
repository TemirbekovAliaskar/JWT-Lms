package java12.dto.response;

import java.time.ZonedDateTime;
public record TaskResponse (Long id, String taskName, String taskText, ZonedDateTime deadLine){
}
