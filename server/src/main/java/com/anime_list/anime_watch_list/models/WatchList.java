package com.anime_list.anime_watch_list.models;

import com.anime_list.anime_watch_list.repositroies.WatchListRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonIgnoreProperties({"watchLists"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "animes_id")
    private Anime watchlistAnime;

    public WatchList(User user, Anime watchlistAnime) {
        this.user = user;
        this.watchlistAnime = watchlistAnime;
    }

    public WatchList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Anime getAnime() {
        return watchlistAnime;
    }

    public void setAnime(Anime anime) {
        this.watchlistAnime = anime;
    }

}
