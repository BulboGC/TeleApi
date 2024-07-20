package com.desertgm.app.Controller;


import com.desertgm.app.DTO.Authentication.AuthenticationDto;
import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.DTO.User.ForgotPassDto;
import com.desertgm.app.DTO.User.UpdatePasswordDto;
import com.desertgm.app.DTO.User.UserDto;
import com.desertgm.app.Models.Email.Email;
import com.desertgm.app.Models.User.User;
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


    @PostMapping("/login")
    public ResponseEntity<NewResponseDto> login(@RequestBody AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        NewResponseDto responseDto = new NewResponseDto(
                "Login Realizado com sucesso",
                "OK",
                token
        );

        return ResponseEntity.ok().body(responseDto);
    }

    
    @PostMapping("/register")//usuario comum 1 ADM 2 Supervisor 3
    public ResponseEntity<NewResponseDto> register(@RequestBody UserDto userDto){
            var user = userService.ParseDtoToUser(userDto);
            NewResponseDto responseDto = new NewResponseDto(
                    
                    "Usuario salvo com sucesso",
                    "OK",user
            );
            return  ResponseEntity.ok().body(responseDto);
    }


    @GetMapping("/token/{token}")
    public ResponseEntity<NewResponseDto> verifyToken(@PathVariable String token){
        var validatetoken = tokenService.validateToken(token);
        if(validatetoken == ""){
            throw new RuntimeException("TokenExpirado");
        }
        if(validatetoken != null|| validatetoken != ""){
            NewResponseDto responseDto1 = new NewResponseDto("TokenExpirado","OK",validatetoken);
            return ResponseEntity.ok().body(responseDto1);
        }
        throw new RuntimeException("Erro ao verificar o Token");

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<NewResponseDto> forgotPass(@RequestBody ForgotPassDto forgotPassDto){
        User user = userService.getUserByEmail(forgotPassDto.email());
        if(user == null) {
            throw  new RuntimeException("o email informado não é cadastrado no sistema");
        }
        else {
            Email email = emailService.sendRecoveryPass(forgotPassDto.email().toLowerCase());
            emailService.sendEmail(email);
            NewResponseDto responseDto =    new NewResponseDto("email enviado","OK",null);
            return ResponseEntity.ok().body(responseDto);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<NewResponseDto> resetPassword(@RequestBody UpdatePasswordDto updatePasswordDto){

        User user =  recoveryTokenService.getUserWithToken(updatePasswordDto.token());
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(updatePasswordDto.password());
        user.setPassword(encryptedPassword);
        userService.addUserorUpdate(user);
        NewResponseDto responseDto = new NewResponseDto("Senha atualizada.","OK",null);
        return ResponseEntity.ok().body(responseDto);
    }




}
