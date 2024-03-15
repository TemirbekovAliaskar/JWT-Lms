package java12.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@Builder
public class HTTPResponse {
    private HttpStatus httpStatus;
    private String message;
    private String token;
}
