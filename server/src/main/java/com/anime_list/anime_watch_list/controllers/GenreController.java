package com.anime_list.anime_watch_list.controllers;

import com.anime_list.anime_watch_list.models.Anime;
import com.anime_list.anime_watch_list.models.Genre;
import com.anime_list.anime_watch_list.models.WatchList;
import com.anime_list.anime_watch_list.repositroies.AnimeRepository;
import com.anime_list.anime_watch_list.repositroies.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AnimeRepository animeRepository;

    // Get
    @GetMapping
    public ResponseEntity<List<Genre>> getGenres(){
        return new ResponseEntity<>(genreRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}") //localhost:8080/genres/1
    public ResponseEntity<Optional<Genre>> getGenre(@PathVariable Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return new ResponseEntity<>(genre, genre.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    // Post
    @PostMapping
    public ResponseEntity<Genre> createGenre (@RequestBody Genre newGenre){
        genreRepository.save(newGenre);
        return new ResponseEntity<>(newGenre, HttpStatus.CREATED);
    }

    // Put
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre (@PathVariable(value = "id") Long id, @RequestBody Genre genreDetailUpdate){
        var genre = genreRepository.findById(id);
        if(genre.isPresent()){
            Genre g = genre.get();
            g.setName(genreDetailUpdate.getName());
            g.setAnimes(genreDetailUpdate.getAnimes());
            genreRepository.save(g);
            return new ResponseEntity<>(g, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//  Delete -> Working ✅
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Genre>> deleteGenre(@PathVariable Long id) {
        var found = genreRepository.findById(id);
        genreRepository.deleteById(id);
        return new ResponseEntity(genreRepository.findAll(), found.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
