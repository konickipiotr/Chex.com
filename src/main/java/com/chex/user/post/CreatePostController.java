package com.chex.user.post;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chex.db.user.PostRepo;
import com.chex.model.achievement.Achievement;
import com.chex.user.place.ReturnPlace;

@RestController
@RequestMapping("user/createpost")
public class CreatePostController {

	@Autowired
	private PostRepo postRepo;
	
	@PostMapping("/place")
	public Long createPosts(@RequestBody ReturnPlace rp, @RequestBody Post placePost) {
		placePost.setDate(Date.valueOf(LocalDate.now()));
		placePost.setPlaceid(rp.getPlace().getIdplace());
		placePost.setPostStatus(PostStatus.PUBLIC);
		placePost.setPostType(PostType.PLACE);
		postRepo.save(placePost);
		createAchievemenPosts(rp, placePost);
		return placePost.getPostid();
	}
	
	private void createAchievemenPosts(ReturnPlace rp, Post post) {
		for(Achievement a : rp.getAchievementList()) {
			Post pa = new Post();
			pa.setAchievementid(a.getIdachievement());
			pa.setContent(a.getDescription());
			pa.setDate(post.getDate());
			pa.setPhotoUrl(a.getImgurl());
			pa.setPostStatus(PostStatus.PUBLIC);
			pa.setPostType(PostType.ACHIEVEMENT);
			postRepo.save(pa);
		}
	}
}
