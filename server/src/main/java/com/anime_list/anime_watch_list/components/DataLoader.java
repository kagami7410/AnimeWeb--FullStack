package com.anime_list.anime_watch_list.components;


import com.anime_list.anime_watch_list.models.Anime;
import com.anime_list.anime_watch_list.models.Genre;
import com.anime_list.anime_watch_list.models.User;
import com.anime_list.anime_watch_list.models.WatchList;
import com.anime_list.anime_watch_list.repositroies.AnimeRepository;
import com.anime_list.anime_watch_list.repositroies.GenreRepository;
import com.anime_list.anime_watch_list.repositroies.UserRepository;
import com.anime_list.anime_watch_list.repositroies.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WatchListRepository watchListRepository;

    @Autowired
    GenreRepository genreRepository;


    @Override
    public void run (ApplicationArguments args) throws Exception {




        //      Add Anime
        Anime anime1 = new Anime("Naruto", LocalDate.of(2002, 10, 03), "Ninja's",  4.6, 500);
        Anime anime2 = new Anime("Bleach", LocalDate.of(2004, 10, 05), "Soul reaper's", 4.5, 366);
        Anime anime3 = new Anime("Attack on Titan", LocalDate.of(2013, 04, 07), "Titans destroy humanity",  4.5, 86);
        Anime anime4 = new Anime("One Piece", LocalDate.of(1999, 10, 03), "Pirates",  4.5, 1014);
        Anime anime5 = new Anime("Haikyuu", LocalDate.of(2014, 04, 06), "High school volley ball", 4.5, 73);
        Anime anime6 = new Anime("Kuroko's Basketball", LocalDate.of(2012, 04, 07), "High school basket ball team", 4.5, 75);
        Anime anime7 = new Anime("Bungo Stray Dogs", LocalDate.of(2016, 04, 07), "Detectives ", 4.5, 500);
        Anime anime8 = new Anime("Death Note", LocalDate.of(2002, 10, 03), "Ninja anime", 4.5, 500);
        Anime anime9 = new Anime("Slam Dunk", LocalDate.of(2002, 10, 03), "Ninja anime", 4.5, 500);
        Anime anime10 = new Anime("Jojo's Bizzare Adventure", LocalDate.of(2002, 10, 03), "Ninja anime", 4.5, 500);



        //      Add Genre
        Genre genre1 = new Genre("comedy", Arrays.asList(anime1,anime2,anime3));
        Genre genre2 = new Genre("Action", Arrays.asList(anime1,anime7,anime3));
        Genre genre3 = new Genre("Ecchi", Arrays.asList(anime4,anime2,anime3, anime8));


        anime1.addGenres(Arrays.asList(genre1, genre2));
        anime2.addGenres(Arrays.asList(genre2, genre3));


//        Anime anime1 = new Anime("Naruto", LocalDate.of(2002, 10, 03), "Ninja's", Arrays.asList("Action", "Shounen","Comedy", "SuperPower","Adventure"), 4.6, 500);
//        Anime anime2 = new Anime("Bleach", LocalDate.of(2004, 10, 05), "Soul reaper's", Arrays.asList("Action", "Shounen","Comedy", "SuperPower"), 4.5, 366);
//        Anime anime3 = new Anime("Attack on Titan", LocalDate.of(2013, 04, 07), "Titans destroy humanity", Arrays.asList("Action", "Fantasy","Shounen","Comedy", "SuperPower"), 4.5, 86);
//        Anime anime4 = new Anime("One Piece", LocalDate.of(1999, 10, 03), "Pirates", Arrays.asList( "Fantasy","Shounen","Comedy", "SuperPower","Adventure"), 4.5, 1014);
//        Anime anime5 = new Anime("Haikyuu", LocalDate.of(2014, 04, 06), "High school volley ball", Arrays.asList( "Fantasy","Shounen","Comedy", "SuperPower","Adventure"), 4.5, 73);
//        Anime anime6 = new Anime("Kuroko's Basketball", LocalDate.of(2012, 04, 07), "High school basket ball team", Arrays.asList("Fantasy","Shounen","Comedy", "SuperPower","Adventure", "Sports"), 4.5, 75);
//        Anime anime7 = new Anime("Bungo Stray Dogs", LocalDate.of(2016, 04, 07), "Detectives ", Arrays.asList("Fantasy","Shounen","Comedy", "SuperPower","Adventure"), 4.5, 500);
//        Anime anime8 = new Anime("Death Note", LocalDate.of(2002, 10, 03), "Ninja anime", Arrays.asList( "Fantasy","Shounen","Comedy", "SuperPower","Adventure", "Horror"), 4.5, 500);
//        Anime anime9 = new Anime("Slam Dunk", LocalDate.of(2002, 10, 03), "Ninja anime", Arrays.asList( "Fantasy","Shounen","Comedy", "SuperPower","Sports"), 4.5, 500);
//        Anime anime10 = new Anime("Jojo's Bizzare Adventure", LocalDate.of(2002, 10, 03), "Ninja anime", Arrays.asList( "Fantasy","Comedy", "SuperPower","Adventure"), 4.5, 500);


        //      Add User
        User user1 = new User("Onoda", LocalDate.of(1999,9,2), "onodaKun@gmail.com");
        User user2 = new User("John", LocalDate.of(1959,6,25),"lol@gmail.com");
        User user3 = new User("Rem", LocalDate.of(2001,9,4), "Kem@gmail.com");
        User user4 = new User("Jenny", LocalDate.of(1969,3,27), "janjenny@gmail.com");
        User user5 = new User("Shiki", LocalDate.of(1999,9,14), "Shiki43@gmail.com");
        User user6 = new User("Randy", LocalDate.of(1599,5,15), "Randy432@gmail.com");
        User user7 = new User("Shuka", LocalDate.of(1999,9,2), "onodaKun@gmail.com");
        User user8 = new User("Obu", LocalDate.of(2003,9,3), "ObiObi@gmail.com");
        User user9 = new User("Kite", LocalDate.of(1998,6,6), "Kie7673@gmail.com");
        User user10 = new User("Pete", LocalDate.of(1987,9,23), "PITY@gmail.com");
        User user11 = new User("Kwnichi", LocalDate.of(1989,6,24), "koitd@gmail.com");
        User user12 = new User("Quinnie", LocalDate.of(2001,9,2), "qunie432@gmail.com");

        WatchList watchList1 = new WatchList(user1, anime1);
        WatchList watchList2 = new WatchList(user1, anime2);
        WatchList watchList3 = new WatchList(user1, anime3);

        WatchList watchList4 = new WatchList(user2, anime4);
        WatchList watchList5 = new WatchList(user2, anime5);

        animeRepository.saveAll(Arrays.asList(anime1, anime2, anime3, anime4, anime5, anime6, anime7, anime8, anime9, anime10));
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12));
        watchListRepository.saveAll(Arrays.asList(watchList1,watchList2,watchList3,watchList4,watchList5));
        genreRepository.saveAll(Arrays.asList(genre1, genre2, genre3));

    }
}
