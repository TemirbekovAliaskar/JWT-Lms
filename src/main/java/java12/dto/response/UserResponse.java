package java12.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(String token,Long id,String name,String email,String password) {
}
