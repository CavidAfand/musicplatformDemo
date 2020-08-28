package com.musicplatform.repositories;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Music;
import com.musicplatform.entities.Musician;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.List;


public interface MusicRepository extends JpaRepository<Music, Integer> {

    Music findMusicByMusicPath(String path);
    List<Music> findAllByMusicianAndMusicNameContainingOrderByIdDesc(Musician musician, String musicName);
    List<Music> findAllByBandAndMusicNameContainingOrderByIdDesc(Band band, String musicName);
    Page<Music> findAllByMusicPathIsNotInOrderByIdDesc(Pageable page, Collection<String> musicNames);

    @Query(value = "select m from Music m where m.musicName like %:searchWord%")
    List <Music> searchMusicByMusicName(@Param("searchWord") String searchWord);

}
