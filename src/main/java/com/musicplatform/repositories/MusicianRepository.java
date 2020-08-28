package com.musicplatform.repositories;

import com.musicplatform.entities.Musician;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MusicianRepository extends JpaRepository<Musician, Integer> {

    @Query(value="select m from Musician m where m.email = :email")
    public Musician findByEmail(@Param("email") String email);

    public Musician findByUsername(String username);

    Page<Musician> findAllByUsernameIsNotInOrderByIdDesc(Pageable page, Collection<String> usernameList);

}
