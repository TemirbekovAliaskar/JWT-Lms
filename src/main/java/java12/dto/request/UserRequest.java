package java12.dto.request;

import java12.entity.enums.Role;

public record UserRequest(String name, String email, String password, String passwordConfirm, Role role) {
}
