package com.desertgm.app.Controller;

import com.desertgm.app.DTO.UserDto;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.UserModel;
import com.desertgm.app.Repositories.UserRepository;
import com.desertgm.app.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
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


}
