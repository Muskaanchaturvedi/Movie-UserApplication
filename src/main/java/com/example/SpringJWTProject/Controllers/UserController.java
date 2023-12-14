package com.example.SpringJWTProject.Controllers;

import com.example.SpringJWTProject.Model.User;
import com.example.SpringJWTProject.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    //Creating a User
    @PostMapping(value = "/add-user")
    public String Signup(@RequestBody User user){
        return userService.signup(user);
    }
    //Login User

    @PostMapping(value = "/login")
    public String login(@RequestBody Map<String,Object> map){
        return userService.login(map.get("email").toString(),map.get("password").toString());
    }
    //Get User
    @GetMapping(value = "/get-user/{id}")
    public User getUser(@PathVariable("id") ObjectId id){

        return userService.getUser(id);
    }

    @GetMapping(value="/{age}")
    public List<User> getUserLessThanParticularAge( @PathVariable  int age){
        return userService.getUserofLessThanParticularAge(age);
    }

    @PostMapping(value="/user-byName-byEmail")
    public List<User> getUserByNameorEmail(@RequestBody Map<String,Object> map){
        return userService.getUserByNameorEmail(map.get("name").toString(),map.get("email").toString());
    }

    @GetMapping(value="/sort-by-name/{age}")
    public List<User> getUserByAgeSortByName(@PathVariable("age") int age){
        System.out.println(age);
        return userService.getUserByAgeSortByName(age);
    }

}
