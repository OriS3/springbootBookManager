package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringBootRegisterTestApplicationTests {

    @Test
    void test() {
        System.out.println(new BCryptPasswordEncoder().matches("123456", "$2a$10$or4akMfqrFg1OnU6Xrz4Zu.ca4yWdLoYEje3LoCJucCqo128cnjsi"));
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
