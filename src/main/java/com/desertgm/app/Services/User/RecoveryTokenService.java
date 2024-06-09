package com.desertgm.app.Services.User;

import com.desertgm.app.Models.User.RecoveryToken;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.RecoveryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecoveryTokenService {
    @Autowired
    RecoveryTokenRepository recoveryTokenRepository;
    @Autowired
    UserService userService;

    public RecoveryToken addRecoveryToken(RecoveryToken recoveryToken){
         return recoveryTokenRepository.save(recoveryToken);
    }

    public User getUserWithToken(String token){
        Optional<RecoveryToken> recoveryToken =  recoveryTokenRepository.findById(token);
        if( recoveryToken.isPresent()){
            String userId = recoveryToken.get().getUserId();
            return userService.getUserById(userId);
        }
        else {
            return null;
        }
    }

}
