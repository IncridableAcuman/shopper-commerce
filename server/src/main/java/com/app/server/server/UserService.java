package com.app.server.server;

import com.app.server.dto.RegisterRequest;
import com.app.server.entity.User;
import com.app.server.enums.Role;
import com.app.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already exist");
        }
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
    public User findUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("User not found!"));
    }
    public void validatePassword(String password,String userPassword){
        if(!passwordEncoder.matches(password,userPassword)){
            throw new IllegalArgumentException("Password is not equal");
        }
    }
    public void updatePassword(String password){
        User user=new User();
        user.setPassword(passwordEncoder.encode(password));
         userRepository.save(user);
    }
}
