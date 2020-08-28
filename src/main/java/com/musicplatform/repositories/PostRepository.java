package com.musicplatform.repositories;


import com.musicplatform.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostByImagePath(String path);

}
