package com.chex.user.post.display;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chex.db.achievement.AchievementRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.db.user.PostRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.place.Place;
import com.chex.model.user.UserBasic;
import com.chex.user.post.Post;
import com.chex.user.post.PostStatus;
import com.chex.user.post.PostType;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostListControllerTest {
	
	private UserBasicRepo userBasicRepo;
	private PlaceRepo placeRepo;
	private AchievementRepo achievementRepo;
	private TestRestTemplate restT;
	private PostRepo postRepo;
	
	@Autowired
	public PostListControllerTest(UserBasicRepo userBasicRepo, PlaceRepo placeRepo, AchievementRepo achievementRepo,
			TestRestTemplate restT, PostRepo postRepo) {
		this.userBasicRepo = userBasicRepo;
		this.placeRepo = placeRepo;
		this.achievementRepo = achievementRepo;
		this.restT = restT;
		this.postRepo = postRepo;
	}

	Post post = new Post(Date.valueOf("2020-04-01"), 101l, null, "dd", null, PostType.PLACE, "elo elo", "https://live.staticflickr.com/8244/8565033673_70fd159e1b_b.jpg", PostStatus.PUBLIC);
	UserBasic ub = new UserBasic(101l, "Jan", "Nowak", "M", "Wroław");
	Place p = new Place("dd", "zz", 5, 5, 5, "aa", "34", 5, 2);

	@BeforeEach
	void setUp() throws Exception {
		//this.userBasicRepo.deleteAll();
		//this.placeRepo.deleteAll();
		//this.postRepo.deleteAll();
		userBasicRepo.save(ub);
		placeRepo.save(p);
		postRepo.save(post);
	}

	@Test
	void ifPortalHasntAnyUserReturnEmptyListOfPost() {
		ResponseEntity<PostListWraper> response = restT.getForEntity("http://localhost:8084/user/", PostListWraper.class);
		
		//when(postRepo.findAll()).thenReturn(new ArrayList());
		
		List<PostView>  postViewList = response.getBody().getPosts();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, postViewList.size());
	}
	
	@Test
	public void onePostInDB() {
		
		
		
//		when(postRepo.findAll()).thenReturn(Arrays.asList(post));
//		when(user.g)
//		when(userBasicRepo.findById(101l)).thenReturn(Optional.of(ub));
//		when(placeRepo.findByIdplace("dd")).thenReturn(p);
		ResponseEntity<PostListWraper> response = restT.getForEntity("http://localhost:8084/user/", PostListWraper.class);
		
		List<PostView>  postViewList = response.getBody().getPosts();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, postViewList.size());
	}
}
