package com.portfolio.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEmcoderGenerator {
  public static void main(String[] args) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    System.out.println(passwordEncoder.encode("password"));
  }
}
