package com.desertgm.app.Services;

import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.User;
import com.desertgm.app.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUserorUpdate(User user){

        userRepository.save(user);
    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserById(String id){
      Optional<User> user0 =  userRepository.findById(id);

      if(user0.isEmpty()){
          throw new Error("id enviado não existe");
      }
      return user0.get();

    }
    public boolean userExist(String email){
        User user0 = userRepository.findByEmail(email);
        return user0 != null;
    }

    public List<User> findAllSupervisors(){

        return userRepository.findByRole(UserRole.SUPERVISOR.getRoleValue());

    }


}

