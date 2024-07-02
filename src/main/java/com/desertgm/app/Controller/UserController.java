package com.desertgm.app.Controller;
import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/ByRole")
    public ResponseEntity<NewResponseDto> getUserProfile(@RequestAttribute String userId) {
        User user =  userService.getUserById(userId);
        var users = userService.getUserbyRole(user);
        NewResponseDto responseDto = new NewResponseDto("transação efeituada com sucesso","OK",users);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/")
    public ResponseEntity<NewResponseDto> getUser(@RequestAttribute String userId){
        User user = userService.getUserById(userId);
        NewResponseDto responseDto = new NewResponseDto("transação efeituada com sucesso","OK",user);
        return ResponseEntity.ok().body(responseDto);
    }


    @GetMapping("/Supervisors")
    public ResponseEntity<NewResponseDto> getAllSupervisors(){

       List<User> userList =  userService.findAllSupervisors();
        NewResponseDto responseDto = new NewResponseDto("transação efeituada com sucesso","OK", userList);
       return  ResponseEntity.ok().body(responseDto);
    }



}
