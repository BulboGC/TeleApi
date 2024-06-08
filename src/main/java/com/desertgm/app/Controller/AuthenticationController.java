package com.desertgm.app.Controller;


import com.desertgm.app.DTO.AuthenticationDto;
import com.desertgm.app.DTO.ResponseDto;
import com.desertgm.app.DTO.UserDto;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.User;
import com.desertgm.app.Services.TokenService;
import com.desertgm.app.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> login(@RequestBody AuthenticationDto data){
        HashMap<String,String> mapReturn = new HashMap<>();

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        mapReturn.put("token",token);

        return ResponseEntity.ok().body(mapReturn);
    }

    // criar parametro para trocar entre admin e usuario comum
    @PostMapping("/register")
    public ResponseEntity<HashMap<String,String>> register(@RequestBody UserDto userDto){

        if(userService.userExist(userDto.email())) return ResponseEntity.badRequest().build();
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());

            User user = new User(encryptedPassword,userDto.email(),userDto.username(), UserRole.USER.getRoleValue());
            userService.addUserorUpdate(user);
            userService.addUserorUpdate(user);

            ResponseDto responseDto = new ResponseDto();
            responseDto.addResponse("msg","Usuario salvo com sucesso");
            responseDto.addResponse("status","OK");
            return  ResponseEntity.ok().body(responseDto.getResponse());
        }
    }
    @PostMapping("/register-Super")
    public ResponseEntity<HashMap<String,String>> registerS(@RequestBody UserDto userDto){

        if(userService.userExist(userDto.email())) return ResponseEntity.badRequest().build();
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());

            User user = new User(encryptedPassword,userDto.email(),userDto.username(), UserRole.SUPERVISOR.getRoleValue());
            userService.addUserorUpdate(user);
            userService.addUserorUpdate(user);

            ResponseDto responseDto = new ResponseDto();
            responseDto.addResponse("msg","Usuario salvo com sucesso");
            responseDto.addResponse("status","OK");
            return  ResponseEntity.ok().body(responseDto.getResponse());
        }
    }

    @PostMapping("/register-ADM")
    public ResponseEntity<HashMap<String,String>> registerAdm(@RequestBody UserDto userDto){
        if(userService.userExist(userDto.email())) return ResponseEntity.badRequest().build();
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());

            User user = new User(encryptedPassword,userDto.email(),userDto.username(), UserRole.ADMIN.getRoleValue());
            userService.addUserorUpdate(user);
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



}
