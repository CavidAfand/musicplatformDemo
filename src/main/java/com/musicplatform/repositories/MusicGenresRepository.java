package com.musicplatform.repositories;

import com.musicplatform.entities.MusicGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface MusicGenresRepository extends JpaRepository<MusicGenre, Integer> {

    @Query(value = "select g.genre from MusicGenre g")
    List<String> findAllGenres();

    Set<MusicGenre> findAllByGenreIn(Collection<String> genres);

}
