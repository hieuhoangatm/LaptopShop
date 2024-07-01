package com.dinhhieu.JavaSpringUltimate.service;

import com.dinhhieu.JavaSpringUltimate.DTO.RegisterDTO;
import com.dinhhieu.JavaSpringUltimate.domain.Role;
import com.dinhhieu.JavaSpringUltimate.domain.User;
import com.dinhhieu.JavaSpringUltimate.repository.RoleRepository;
import com.dinhhieu.JavaSpringUltimate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello(){

        return "hi Userservice";
    }

    public  User handleSaveUser(User user){
        return this.userRepository.save(user);
    }

    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(long id ){
        return this.userRepository.findById(id);
    }

    public List<User> getUserByEmail(String email){
        return this.userRepository.findOneByEmail(email);
    }

    public void deleteUserById(long id){
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName()+" "+registerDTO.getLastName());
        user.setPassword(registerDTO.getPassword());
        user.setEmail(registerDTO.getEmail());
        return user;
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmailExits(String email){
        return this.userRepository.findByEmail(email);
    }
}
