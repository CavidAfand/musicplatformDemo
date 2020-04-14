package com.musicplatform.repositories;

import com.musicplatform.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Integer> {
}
