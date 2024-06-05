package com.desertgm.app.Controller;

import com.desertgm.app.Repositories.UserRepository;
import com.desertgm.app.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
