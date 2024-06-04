package com.desertgm.app.Services;

import com.desertgm.app.Models.UserModel;
import com.desertgm.app.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(UserModel userModel){

        userRepository.save(userModel);
    }
    public UserModel getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public UserModel getUserById(String id){
      Optional< UserModel> user0 =  userRepository.findById(id);

      if(user0.isEmpty()){
          throw new Error("id enviado não existe");
      }
      return user0.get();

    }
    public boolean userExist(String email){
        UserModel user0 = userRepository.findByEmail(email);
        return user0 != null;
    }



}

