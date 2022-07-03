package com.anime_list.anime_watch_list.controllers;

import com.anime_list.anime_watch_list.models.Anime;
import com.anime_list.anime_watch_list.models.Genre;
import com.anime_list.anime_watch_list.models.User;
import com.anime_list.anime_watch_list.repositroies.UserRepository;
import com.anime_list.anime_watch_list.repositroies.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    // Get
    @GetMapping // localhost:8080/users
        public ResponseEntity <List<User>> getUser(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // localhost:8080/user/X
    public ResponseEntity<User> getUser(@PathVariable Long id){
        var found = userRepository.findById(id);
        return new ResponseEntity(userRepository.findById(id), found.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

//    SHOW

    @GetMapping(value = "/name={name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        List<User> userByName = userRepository.findUsersByName(name);
        return new ResponseEntity<>(userByName, HttpStatus.OK);
    }

    @GetMapping(value = "/dob={dob}")
    public ResponseEntity<List<User>> getUsersByDob (@PathVariable LocalDate dob){
        List<User> userByDob = userRepository.findUserByDobAfter(dob);
        return new ResponseEntity<>(userByDob, HttpStatus.OK);
    }

    @GetMapping(value = "/email={email}")
    public ResponseEntity<List<User>> getUsersByEmail (@PathVariable String email){
        List<User> userByEmail = userRepository.findUserByEmail(email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    //POST
    @PostMapping
    public ResponseEntity<List<User>> createUser(@RequestBody User newUser){
        userRepository.save(newUser);
        return new ResponseEntity(userRepository.findAll(), HttpStatus.CREATED);
    }

    // Put
    @PutMapping(value = "/{id}") // localhost:8080/users/X
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User upUser) throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("User with id: " + id + " not found");
        } else {
            User u = user.get();
            u.setName(upUser.getName());
            u.setDob(upUser.getDob());
            u.setEmail(upUser.getEmail());

            User updatedUser = userRepository.save(u);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
    }

    // Delete -> Working ✅
    // Deleting the user also deletes all of their "Watch Lists", this is good
    // We want to delete all of their "Watch Lists" too as they no longer have
    // an account, so we do not need their data anymore
    @DeleteMapping(value = "/{id}") // localhost:8080/users/X
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User upUser = user.get();
            upUser.getWatchLists().stream()
                    .forEach(watchList -> watchListRepository.deleteById(watchList.getId()));
            upUser.setWatchLists(new ArrayList<>());
            userRepository.deleteById(id);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
