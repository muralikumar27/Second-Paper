package com.murali.secondpaper.controller;

import com.murali.secondpaper.model.SignupDto;
import com.murali.secondpaper.response.ApiResponse;
import com.murali.secondpaper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v0/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupDto signupDto, HttpServletRequest request) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(userService.registerUser(signupDto, request), "Sign up Successful"), HttpStatus.OK);
    }

    @GetMapping("/verify-user")
    public ResponseEntity<ApiResponse> verifyAccount(@RequestParam String token) {
        return new ResponseEntity<>(new ApiResponse(null, userService.VerifyUser(token)), HttpStatus.OK);
    }
}
