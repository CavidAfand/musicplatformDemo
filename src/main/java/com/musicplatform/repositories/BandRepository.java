package com.musicplatform.repositories;

import com.musicplatform.entities.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BandRepository extends JpaRepository<Band, Integer> {

    @Query(value="select b from Band b where b.email = :email")
    public Band findByEmail(@Param("email") String email);

    @Query(value = "select b from Band b where b.username = :username")
    public Band findByUsername(@Param("username") String username);
}
