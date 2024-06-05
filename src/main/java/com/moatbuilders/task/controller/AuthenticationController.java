package com.moatbuilders.task.controller;

import com.moatbuilders.task.domian.user.AuthenticationDTO;
import com.moatbuilders.task.domian.user.LoginResponseDTO;
import com.moatbuilders.task.domian.user.RegisterDTO;
import com.moatbuilders.task.domian.user.UserEntity;
import com.moatbuilders.task.infra.security.TokenService;
import com.moatbuilders.task.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body((new LoginResponseDTO(token)));

    }

    @GetMapping("/validate")
    public ResponseEntity validateToken(@RequestParam("token") String token) {

        var login = tokenService.validateToken(token);

        if (login.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.fullName(), data.username(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}