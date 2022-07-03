package com.anime_list.anime_watch_list.controllers;

import com.anime_list.anime_watch_list.models.Anime;
import com.anime_list.anime_watch_list.models.User;
import com.anime_list.anime_watch_list.models.WatchList;
import com.anime_list.anime_watch_list.repositroies.AnimeRepository;
import com.anime_list.anime_watch_list.repositroies.GenreRepository;
import com.anime_list.anime_watch_list.repositroies.UserRepository;
import com.anime_list.anime_watch_list.repositroies.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("animes")
public class AnimeController{

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WatchListRepository watchListRepository;

//    GET MAPPING
    @GetMapping //localhost:8080/animes
    public ResponseEntity<List<Anime>> getAnime(){
        return new ResponseEntity(animeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}") //localhost:8080/animes/X
    public ResponseEntity<Optional<Anime>> getAnime(@PathVariable Long id) {
        Optional<Anime> anime = animeRepository.findById(id);
        return new ResponseEntity<>(anime, anime.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

//    SHOW

    @GetMapping(value = "/name={name}")
    public ResponseEntity<List<Anime>> getAnimesByName(@PathVariable String name){
        List<Anime> animeByName = animeRepository.findAnimeByName((name));
        return new ResponseEntity<>(animeByName, HttpStatus.OK);
    }

    @GetMapping(value = "/description={description}")
    public ResponseEntity<List<Anime>> getAnimesByDescription(@PathVariable String description){
        List<Anime> animeByDes = animeRepository.findAnimeByDescriptionStartingWith(description);
        return new ResponseEntity<>(animeByDes, HttpStatus.OK);
    }

    @GetMapping(value = "/rating>{rating}")
    public ResponseEntity<List<Anime>> getAnimesByRatingGreaterThan(@PathVariable double rating){
        List<Anime> animeByRating = animeRepository.findAnimeByRatingGreaterThan(rating);
        return new ResponseEntity<>(animeByRating, HttpStatus.OK);
    }

    @GetMapping(value = "/episodesGreaterThan>{numberOfEps}")
    public ResponseEntity<List<Anime>> getAnimesByEpsGreaterThan(@PathVariable int numberOfEps){
        List<Anime> animeByEps = animeRepository.findAnimeByNumberOfEpsGreaterThan(numberOfEps);
        return new ResponseEntity<>(animeByEps, HttpStatus.OK);
    }

    @GetMapping(value = "/releaseDate") // not working rn (-_-)
    public ResponseEntity<List<Anime>> getAnimesByReleaseDateGreaterThan(@PathVariable LocalDate releaseDate){
        List<Anime> animeRD = animeRepository.findAnimeByReleaseDateGreaterThan(releaseDate);
        return new ResponseEntity<>(animeRD, HttpStatus.OK);
    }



    // POST MAPPING
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Anime>> createAnime(@RequestBody Anime newAnime){
        animeRepository.save(newAnime);
        return new ResponseEntity(animeRepository.findAll(), HttpStatus.CREATED);
    }

    // Put
    @PutMapping(value = "/{id}") //localhost:8080/animes/1
    public ResponseEntity<Anime> updateAnime(@PathVariable(value = "id") Long id, @RequestBody Anime upAnime) throws Exception{
        Optional<Anime> anime = animeRepository.findById(id);
        if(anime.isEmpty()){
            throw new Exception("Anime with id: " + id + " not found");
        } else {
            Anime a = anime.get();
            a.setName(upAnime.getName());
            a.setReleaseDate(upAnime.getReleaseDate());
            a.setDescription(upAnime.getDescription());
            a.setRating(upAnime.getRating());
            a.setNumberOfEps(upAnime.getNumberOfEps());

            Anime updatedAnime = animeRepository.save(a);
            return new ResponseEntity<>(updatedAnime,HttpStatus.OK);
        }
    }

    // Delete
    // If we want to delete an anime we must remove all watch lists containing that anime FIRST,
    // as a watch list cannot have a null anime
    // Working, but error thrown if anime is not in a watchlist -> anime is still deleted though
    // New Bug -> deletes genres from db
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeAnimeInWatchListById(@PathVariable("id") Long id) {
        List<WatchList> AllWatchLists = watchListRepository.findAll();
        Optional<Anime> anime = animeRepository.findById(id);

        for(int i = 0; i < AllWatchLists.size(); i++) {
            if(AllWatchLists.get(i).getAnime().getId().equals(anime.get().getId())) {
                watchListRepository.deleteById(AllWatchLists.get(i).getId());
                animeRepository.deleteById(id);
            } else if(!AllWatchLists.get(i).getAnime().getId().equals(anime.get().getId())){
                animeRepository.deleteById(id);
            }
        }

        return new ResponseEntity("Anime Deleted " + id , HttpStatus.NOT_FOUND);
    }

//    else if(AllWatchLists.get(i).getAnime().equals(anime.)) {
//        animeRepository.deleteById(id);
//    }
//    AllWatchLists.size() < anime.get().getId()
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> removeAnimeNotInWatchListById(@PathVariable Long id) {
//        var found = animeRepository.findById(id);
//        animeRepository.deleteById(id);
//        return new ResponseEntity("Anime Deleted id: " + id, HttpStatus.NOT_FOUND);
//    }

}