package com.musicplatform.repositories;

import com.musicplatform.entities.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MusicianRepository extends JpaRepository<Musician, Integer> {

    @Query(value="select m from Musician m where m.email = :email")
    public Musician findByEmail(@Param("email") String email);

//    @Query(value="select m from Musician m where m.username = :username")
    public Musician findByUsername(@Param("username") String username);

}
