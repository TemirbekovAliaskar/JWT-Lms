package java12.service.impl;

import java12.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitalService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
//        @PostConstruct
//    public void saveData() {
//
//            String encode = passwordEncoder.encode("java12");
//            User admin = User.builder()
//                    .email("admin@gmail.com")
//                    .password(encode)
//                    .name("Admin")
//                    .role(Role.ADMIN)
//                    .build();
//
//            User client = User.builder()
//                    .email("aliaskar@gmail.com")
//                    .password(passwordEncoder.encode("java12"))
//                    .name("Aliaskar")
//                    .role(Role.STUDENT)
//                    .build();
//
//            userRepository.save(admin);
//
//        }

}
