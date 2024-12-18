package com.challenge.challenge.controller;

import com.challenge.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/my-page")
    public ResponseEntity<?> myPage(@RequestParam("id") Long userId) {
        return ResponseEntity.ok(this.userService.getMyPage(userId));
    }




}
