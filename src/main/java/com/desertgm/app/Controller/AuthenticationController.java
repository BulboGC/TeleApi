package com.desertgm.app.Controller;


import com.desertgm.app.DTO.AuthenticationDto;
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

@RestController
@RequestMapping("auth")
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
    public ResponseEntity register(@RequestBody UserDto userDto){

        if(userService.userExist(userDto.email())) return ResponseEntity.badRequest().build();
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());

            User user = new User(encryptedPassword,userDto.email(),userDto.username(), UserRole.ADMIN.getRoleValue());
            userService.addUser(user);
            userService.addUser(user);
            return  ResponseEntity.ok().body("Usuario salvo");
        }
    }

    //  freitas e coutinho inteligência comercial
    //  venda efetivada
    //  socios em cima junto com os dados da empresa
    //

}
