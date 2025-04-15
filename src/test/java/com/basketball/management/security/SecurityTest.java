package com.basketball.management.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class SecurityTest {

    @Test
    public void PasswordEncoderTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("1234");
        System.out.println(encoded);
        // result : $2a$10$rdC5GpDN5AXJSFS3HpeRwO1HDn5xyM9OLUkuf/t3cwSoC6kW1M5qK
    }
}
