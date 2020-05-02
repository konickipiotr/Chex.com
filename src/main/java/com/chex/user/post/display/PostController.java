package com.chex.user.post.display;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chex.db.CommentRepo;
import com.chex.db.PhotoRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.db.user.PostRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.place.Place;
import com.chex.model.user.UserBasic;
import com.chex.user.post.Post;

@RestController
public class PostController {

	private PostRepo postRepo;
	private CommentRepo commentRepo;
	private UserBasicRepo userBasicRepo;
	private PlaceRepo placeRepo;
	private PhotoRepo photoRepo;

	@Autowired
	public PostController(PostRepo postRepo, CommentRepo commentRepo, UserBasicRepo userBasicRepo, PlaceRepo placeRepo,
			PhotoRepo photoRepo) {
		this.postRepo = postRepo;
		this.commentRepo = commentRepo;
		this.userBasicRepo = userBasicRepo;
		this.placeRepo = placeRepo;
		this.photoRepo = photoRepo;
	}

	@GetMapping("/api/post/{postid}")
	public PostPlaceView getPostPlaceView(@PathVariable("postid") Long postid) {
		PostPlaceView ppv = new PostPlaceView();
			
		Optional<Post> posto = postRepo.findById(postid);
		if(posto.isEmpty()) {
			//TODO
		}
		Post post = posto.get();
		
		ppv.setPostid(post.getPostid());
		ppv.setDate(post.getDate());
		ppv.setContent(post.getContent());
		ppv.setPostStatus(post.getPostStatus());
		ppv.setPhotourl(post.getPhotoUrl());
		ppv.setComents(commentRepo.findByPostidOrderByTimestampDesc(post.getPostid()));
		ppv.setPhotos(photoRepo.findByPostid(post.getPostid()));
		setPersonalData(ppv, post);
		setPlace(ppv, post);
		
		return ppv;
	}
	
	private void setPersonalData(PostPlaceView ppv, Post post) {
		UserBasic ub = userBasicRepo.findById(post.getUserid()).get();
		ppv.setUserid(ub.getIduser());
		ppv.setName(ub.getFirstname() + " "+ ub.getLastname());
	}
	
	private void setPlace(PostPlaceView ppv, Post post) {
		Place place = placeRepo.findByIdplace(post.getPlaceid());
		ppv.setPlacename(place.getName());
		ppv.setPlaceid(place.getId());
	}

}
