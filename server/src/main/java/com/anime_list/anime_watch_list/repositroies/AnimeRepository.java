package com.anime_list.anime_watch_list.repositroies;

import com.anime_list.anime_watch_list.models.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

//    NEW
    List<Anime> findAnimeByName(String name);

    List<Anime> findAnimeByReleaseDateGreaterThan(LocalDate releaseDate);

    List<Anime> findAnimeByRatingGreaterThan(double rating);

    List<Anime> findAnimeByNumberOfEpsGreaterThan(int numberOfEps);

    List<Anime> findAnimeByDescriptionStartingWith(String description);

//    @Query("Select a FROM Anime a JOIN a.genres g WHERE g.id =: id")
//    List<Anime> findAnimeByGenreId(Long id);
//    List<Anime> findAllByGenres_GenreName(Long id);

//    @Query("select t from Test t join User u where u.username = :username")
//    List<Test> findAllByUsername(@Param("username")String username);

//    @Query("select a from Anime a join Genre g where g.animes =: anime")
//    List<Anime> findAllByGenres(@RequestParam("comedy")String genre);
    }
