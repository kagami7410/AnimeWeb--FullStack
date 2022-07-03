package com.anime_list.anime_watch_list.repositroies;

import com.anime_list.anime_watch_list.models.Anime;
import com.anime_list.anime_watch_list.models.User;
import com.anime_list.anime_watch_list.models.WatchList;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

//    List<WatchList> findByAnimeId(Long id);
//    List<WatchList> findByWatchListAnime(Anime anime);

}
