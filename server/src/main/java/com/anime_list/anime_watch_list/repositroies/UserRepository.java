package com.anime_list.anime_watch_list.repositroies;

import com.anime_list.anime_watch_list.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findUserByDobAfter(LocalDate dob);

    List<User> findUsersByName(String name);

    List<User> findUserByNameStartingWith(String name);

    List<User> findUserByEmail(String email);

    List<User> findUsersByEmailContaining(String email);
}
