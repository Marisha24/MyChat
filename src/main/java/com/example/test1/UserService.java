package com.example.test1;

import com.example.test1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
private  UserRepository  userRepo;

public com.example.test1.entity.User loadUserByUsername(String username){
    return userRepo.findByUsername(username);
}

}
