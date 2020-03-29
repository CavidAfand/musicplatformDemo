package com.musicplatform.repositories;

import com.musicplatform.entities.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ListenerRepository extends JpaRepository<Listener, Integer> {

    @Query(value="select l from Listener l where l.email = :email")
    public Listener findByEmail(@Param("email") String email);

    @Query(value="select l from Listener l where l.username = :username")
    public Listener findByUsername(@Param("username") String username);
}
