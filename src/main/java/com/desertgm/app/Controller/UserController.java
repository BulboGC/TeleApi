package com.desertgm.app.Controller;

import com.desertgm.app.DTO.UserDto;
import com.desertgm.app.Models.UserModel;
import com.desertgm.app.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        UserModel user0 =   userService.getUserById(id);
        return ResponseEntity.ok().body(user0);
    }

    @PostMapping("/")
    public ResponseEntity<UserModel> addUser(@RequestBody UserDto userDto){
        UserModel user0 = new UserModel();
        BeanUtils.copyProperties(userDto,user0);
        return ResponseEntity.ok().body(user0);
    }

    @GetMapping("/")
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok().body("arena muito burrinho coidtado");
    }



}
