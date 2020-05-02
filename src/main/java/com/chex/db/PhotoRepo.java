package com.chex.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chex.user.album.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {
	List<Photo> findByPostid(Long postid);
}
