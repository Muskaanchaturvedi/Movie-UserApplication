package com.example.SpringJWTProject.Services;

import com.example.SpringJWTProject.Model.User;
import com.example.SpringJWTProject.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository,TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
    //Get a User
    public User getUser(ObjectId id){
        Optional<User> optionalUser=userRepository.getUserById(id);
        return optionalUser.orElseGet(optionalUser::get);
    }
    //List users
    public List<User> getAllUsers(){
        List<User> getUsers = userRepository.findAll();
        return getUsers;
    }
    //Signup an user
    public  String signup(User user){
        User savedUser =userRepository.save(user);
        return
                "{" +
                        "\"message\":"+"Succesfully Created User\",\n"+
                        "\"data\":"+savedUser+",\n"+
            "}";
    }
    //login
    public String login(String email,String password){
        List<User> foundUsers = userRepository.getUserByEmail(email);
        if(foundUsers.isEmpty()){
            return "Authentication Failed User Not Found";
        }else if (!foundUsers.get(0).getPassword().equals(password)){
            return "Password Incorrect";
        }
        return "{\n" +
                "\"message\":"+"\" Successfully Logged-in\",\n"+
                "\"data\": {\n"+" Name : "+foundUsers.get(0).getName()+",\n"+
                "Email : "+foundUsers.get(0).getEmail()+"\n"+
                "\"token\":\""+tokenService.createToken(foundUsers.get(0).getId())+"\"" +
                "}";
    }

    public List<User> getUserofLessThanParticularAge(int age){
        return userRepository.getUserofLessThanParticularAge(age);
    }

    public List<User> getUserByNameorEmail(String name, String email){
        return userRepository.getUserByNameOrEmail(name,email);
    }


    public List<User> getUserByAgeSortByName(int age){
        System.out.println(age+ "service");
        return userRepository.getUserBYAgeSortByName(age);
    }
}
