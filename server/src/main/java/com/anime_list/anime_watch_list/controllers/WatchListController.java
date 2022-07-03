package com.anime_list.anime_watch_list.controllers;

import com.anime_list.anime_watch_list.models.User;
import com.anime_list.anime_watch_list.models.WatchList;
import com.anime_list.anime_watch_list.repositroies.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("watchLists")
public class WatchListController {

    @Autowired
    private WatchListRepository watchListRepository;

    // GET
    @GetMapping // localhost:8080/users
    public ResponseEntity<List<User>> getWatchLists(){
        return new ResponseEntity(watchListRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // localhost:8080/user/X
    public ResponseEntity<User> getWatchList(@PathVariable Long id){
        var found = watchListRepository.findById(id);
        return new ResponseEntity(watchListRepository.findById(id), found.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    // POST

    @PostMapping
    public ResponseEntity<WatchList> createWatchList (@RequestBody WatchList newWatchList){
        watchListRepository.save(newWatchList);
        return new ResponseEntity<>(newWatchList, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<WatchList> updateWatchList (@PathVariable(value = "id") Long id, @RequestBody WatchList watchListDetailUpdate){
        var watchList = watchListRepository.findById(id);
        if(watchList.isPresent()){
            WatchList wl = watchList.get();
            wl.setAnime(watchListDetailUpdate.getAnime());
            wl.setUser(watchListDetailUpdate.getUser());
            watchListRepository.save(wl);
            return new ResponseEntity<>(wl, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // DELETE
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWatchList(@PathVariable Long id) {
        var found = watchListRepository.findById(id);
        watchListRepository.deleteById(id);
        return new ResponseEntity("WatchList Deleted id: " + id, HttpStatus.NOT_FOUND);
    }
}
