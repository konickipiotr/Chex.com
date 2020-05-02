package com.chex.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chex.user.comment.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
	
	List<Comment> findByPostidOrderByTimestampDesc(Long postid);

}
