package com.chex.db.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chex.user.post.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
