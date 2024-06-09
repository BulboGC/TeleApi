package com.desertgm.app.Controller;


import com.desertgm.app.DTO.AuthenticationDto;
import com.desertgm.app.DTO.ResponseDto;
import com.desertgm.app.DTO.User.ForgotPassDto;
import com.desertgm.app.DTO.User.UpdatePasswordDto;
import com.desertgm.app.DTO.User.UserDto;
import com.desertgm.app.Models.Email.Email;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.UserRepository;
import com.desertgm.app.Services.EmailService;
import com.desertgm.app.Services.TokenService;
import com.desertgm.app.Services.User.RecoveryTokenService;
import com.desertgm.app.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RecoveryTokenService recoveryTokenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> login(@RequestBody AuthenticationDto data){
        HashMap<String,String> mapReturn = new HashMap<>();

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        mapReturn.put("token",token);

        return ResponseEntity.ok().body(mapReturn);
    }

    @PostMapping("/register")//usuario comum 1 ADM 2 Supervisor 3
    public ResponseEntity<HashMap<String,String>> register(@RequestBody UserDto userDto){

        if(userService.userExist(userDto.email().toLowerCase())) return ResponseEntity.badRequest().build();
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());

            User user = new User();
            user.setName(userDto.name());
            user.setPassword(encryptedPassword);
            user.setEmail(userDto.email().toLowerCase());
            user.setUsername(userDto.username());
            user.setRole(userDto.role());

            userService.addUserorUpdate(user);


            ResponseDto responseDto = new ResponseDto();
            responseDto.addResponse("msg","Usuario salvo com sucesso");
            responseDto.addResponse("status","OK");
            return  ResponseEntity.ok().body(responseDto.getResponse());
        }
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<HashMap<String,String>> verifyToken(@PathVariable String token){
        var validatetoken = tokenService.validateToken(token);
        ResponseDto responseDto = new ResponseDto();
        if(validatetoken == ""){
            responseDto.addResponse("msg","TokenExpirado");
            responseDto.addResponse("token","");
            return ResponseEntity.badRequest().body(responseDto.getResponse());
        }
        if(validatetoken != null|| validatetoken != ""){
            responseDto.addResponse("msg","TokenExpirado");
            responseDto.addResponse("token",validatetoken);
            return ResponseEntity.ok().body(responseDto.getResponse());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<HashMap<String,String>> forgotPass(@RequestBody ForgotPassDto forgotPassDto){
        User user = userService.getUserByEmail(forgotPassDto.email());
        ResponseDto response = new ResponseDto();

        if(user == null) {
            response.addResponse("msg","o email informado não é cadastrado no sistema");
            return ResponseEntity.badRequest().body(response.getResponse());
        }
        else {
            Email email = emailService.sendRecoveryPass(forgotPassDto.email().toLowerCase());
            emailService.sendEmail(email);
            response.addResponse("msg","email enviado.");
            return ResponseEntity.ok().body(response.getResponse());

        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody UpdatePasswordDto updatePasswordDto){

        User user =  recoveryTokenService.getUserWithToken(updatePasswordDto.token());
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(updatePasswordDto.password());
        user.setPassword(encryptedPassword);
        userService.addUserorUpdate(user);
        return ResponseEntity.ok().build();
    }




}
