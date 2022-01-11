package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.UserRepository;
import com.example.myblogtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(UserDetails user) {
        UserDetails userDb = userRepository.findByEmail(user.getEmail());
        if(userDb == null){
            userRepository.save(user);
            System.out.println(user+" Successfully saved");
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public UserDetails getUserById(Long id){
       return userRepository.findById(id).get();
    }

    @Override
    public void deactivateUser(Long id) {
        //todo implement scheduling
    }
}
