package com.example.service;


import org.springframework.stereotype.Service;

/**
 * @author loop
 */
public interface VerifyService {

    void sendVerifyCode(String mail);

    boolean checkCode(String name, String code);
}
