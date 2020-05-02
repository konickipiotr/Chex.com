package com.chex.user.post.display;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chex.db.achievement.AchievementRepo;
import com.chex.db.place.PlaceDetailRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.db.user.PostRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.achievement.Achievement;
import com.chex.model.place.Place;
import com.chex.model.user.UserBasic;
import com.chex.user.post.Post;
import com.chex.user.post.PostStatus;
import com.chex.user.post.PostType;

@RestController
@RequestMapping("/api")
public class PostListController {

	private PostRepo postRepo;
	private UserBasicRepo userBasicRepo;
	private PlaceRepo placeRepo;
	private AchievementRepo achRepo;
	private PlaceDetailRepo placeDetailsRepo;

	@Autowired
	public PostListController(PostRepo postRepo, UserBasicRepo userBasicRepo, PlaceRepo placeRepo,
			AchievementRepo achRepo, PlaceDetailRepo placeDetailsRepo) {
		super();
		this.postRepo = postRepo;
		this.userBasicRepo = userBasicRepo;
		this.placeRepo = placeRepo;
		this.achRepo = achRepo;
		this.placeDetailsRepo = placeDetailsRepo;
	}

	@GetMapping
	public ResponseEntity<PostListWraper> getlastPosts(){
		List<Post> posts = postRepo.findAll();
		List<PostView> list =preparePostViewList(posts);
		ResponseEntity<PostListWraper> responseEntity = new ResponseEntity<PostListWraper>(new PostListWraper(list), HttpStatus.OK);
		
		return responseEntity;
	}
	
	private List<PostView> preparePostViewList(List<Post> posts){
		List<PostView> pwl = new ArrayList<>();
		for(Post p : posts) {
			if(p.getPostStatus() == PostStatus.HIDDEN) continue;
			Optional<UserBasic> userO = userBasicRepo.findById(p.getUserid());
			
			PostView pw = new PostView();
			if(userO.isPresent()) {
				UserBasic ub = userO.get();
				pw.setName(ub.getFirstname()+ " " + ub.getLastname());
			}else {
				continue;
			}

			pw.setPostid(p.getPostid());
			pw.setDate(p.getDate());
			pw.setUserid(p.getUserid());
			pw.setContent(p.getContent());
			pw.setPhotourl(p.getPhotoUrl());
			
			if(p.getPostType() == PostType.PLACE) {
				Place place = placeRepo.findByIdplace(p.getPlaceid());
				if(place != null) {
					pw.setElementname(place.getName());
					pw.setPathname("/user/place/" + place.getIdplace());
					
					if(p.getPhotoUrl() == null || p.getPhotoUrl().isBlank()) {
						pw.setPhotourl(placeDetailsRepo.findByIdplace(p.getPlaceid()).getImgurl());
					}
				}
			}
			else if(p.getPostType() == PostType.ACHIEVEMENT) {
				Achievement a = achRepo.findById(p.getAchievementid()).get();
				pw.setElementname(a.getName());
				pw.setPathname("/user/achievements/" + a.getIdachievement());
			}
			
			pwl.add(pw);
			
		}
		return pwl;
	}
}
