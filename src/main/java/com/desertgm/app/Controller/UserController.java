package com.desertgm.app.Controller;

import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.UserRepository;
import com.desertgm.app.Services.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public Object getUserProfile(HttpServletRequest request) {

        String userId = (String) request.getAttribute("userId");


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {

            return userRepository.findAll();

        } else {

            return userRepository.findById(userId).orElse(null);
        }

    }
    @GetMapping("/Supervisors")
    public ResponseEntity<List<User>> getAllSupervisors(){
       List<User> userList =  userService.findAllSupervisors();
       return  ResponseEntity.ok().body(userList);
    }

}
