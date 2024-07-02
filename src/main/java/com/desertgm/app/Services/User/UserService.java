package com.desertgm.app.Services.User;

import com.desertgm.app.DTO.User.UserDto;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.prod.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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
        return userRepository.findByEmail(email.toLowerCase());
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


    public List<User> getUserbyRoleint(int role){
      return userRepository.findByRole(role);
    }


    public List<User> getUserbyRole(User user){
        List<User> users = new ArrayList<>();
        switch(UserRole.fromValue(user.getRole())){
            case USER:
                users.add(user);
                return users;

            case SUPERVISOR:
                return this.getUserbyRoleint(UserRole.SUPERVISOR.getRoleValue());

            case ADMIN:
                return userRepository.findAll();

        }
        throw new RuntimeException("Erro na busca do usuario");
    }

    public User ParseDtoToUser(UserDto userDto){

        if(this.userExist(userDto.email().toLowerCase())) throw new Error("usuario já está cadastrado no sistema");
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());

            User user = new User();
            user.setName(userDto.name());
            user.setLastname(userDto.lastname());
            user.setPassword(encryptedPassword);
            user.setEmail(userDto.email().toLowerCase());

            if(userDto.role() != UserRole.ADMIN.getRoleValue()){
                user.setRole(userDto.role());
            }
            
            user.setUsername(userDto.username());
        
            user.setSupervisorId(userDto.supervisorId());
            this.addUserorUpdate(user);
            return  user;
        }

    }


}

