package com.example.controller;


import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
//it is better to obey rest path conventions
@RequestMapping("/mongo")
public class UserController implements ErrorController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public List<User> searchByParam(@RequestParam("id")String id,
                                    @RequestParam("name")String name,@RequestParam("family")String family) {

        return userRepository.findUserByIdOrNameOrFamily(new BigInteger(id),name,family);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Optional<User> getById(@PathVariable BigInteger id) {
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable BigInteger id,@RequestBody User user){
        Optional<User> user1=userRepository.findById(id);
        if(user1.isPresent()){
            user1.get().setFamily(user.getFamily());
            user1.get().setName(user.getName());
            userRepository.save(user1.get());
            return user1.get();
        }
        else
            return null;
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable BigInteger id){
        userRepository.deleteById(id);
    }

    @GetMapping("/getTopByName")
    public User getByName(@RequestParam String name) {
        return userRepository.findTopByName();
    }

    @Override
    public String getErrorPath() {
        return "/get";
    }
}
