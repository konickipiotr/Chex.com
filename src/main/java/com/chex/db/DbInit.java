package com.chex.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chex.db.achievement.AchievementPlacesRepo;
import com.chex.db.achievement.AchievementRepo;
import com.chex.db.achievement.UsersAchievInProgressRepo;
import com.chex.db.achievement.UsersAchievementsRepo;
import com.chex.db.place.PlaceRepo;
import com.chex.db.place.UsersVisitedRepo;
import com.chex.db.user.PostRepo;
import com.chex.db.user.UserAuthRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.Category;
import com.chex.model.MyFriends;
import com.chex.model.achievement.Achievement;
import com.chex.model.achievement.AchievementPlaces;
import com.chex.model.achievement.UsersAchievInProgress;
import com.chex.model.place.Place;
import com.chex.model.place.UsersVisited;
import com.chex.model.user.UserAuth;
import com.chex.model.user.UserBasic;
import com.chex.user.album.Photo;
import com.chex.user.comment.Comment;
import com.chex.user.post.Post;
import com.chex.user.post.PostStatus;
import com.chex.user.post.PostType;

@Service
public class DbInit implements CommandLineRunner {
	
	@Autowired
	private UsersAchievInProgressRepo achInProgressRepo;
	@Autowired
	private PlaceRepo placeRepo;
	@Autowired
	private AchievementRepo achievementRepo;
	@Autowired
	private AchievementPlacesRepo achievementPlaceRepo;
	@Autowired
	private UserAuthRepo userAuthRepo;
	@Autowired
	private UserBasicRepo userBasicRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private PlaceRepo PlaceRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PhotoRepo photoRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UsersAchievementsRepo usersAchievementsRepo;
	@Autowired
	private UsersVisitedRepo usersVisitedRepo;
	@Autowired
	private InvitationRepo invitationRepo;
	@Autowired
	private MyFriendsRepo myFriendsRepo;

	@Override
	public void run(String... args) throws Exception {
		this.achInProgressRepo.deleteAll();
		this.userBasicRepo.deleteAll();
		this.userAuthRepo.deleteAll();
		this.postRepo.deleteAll();
		//this.placeRepo.deleteAll();
		this.photoRepo.deleteAll();
		this.categoryRepo.deleteAll();
		this.achInProgressRepo.deleteAll();
		this.achInProgressRepo.deleteAll();
		this.usersVisitedRepo.deleteAll();
		this.myFriendsRepo.deleteAll();
		this.invitationRepo.deleteAll();
		
		
		Category cat1 = new Category("continent");
		Category cat2 = new Category("country");
		Category cat3 = new Category("regions");
		Category cat4 = new Category("region");
		Category cat5 = new Category("pick");
		this.categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		
		UserAuth ua = new UserAuth("Piotr", passwordEncoder.encode("123"), "USER", 2);
		UserAuth u1 = new UserAuth("jan", passwordEncoder.encode("123"), "USER", 1);
		UserAuth superAdmin = new UserAuth("sa", passwordEncoder.encode("123"), "ADMIN", 2);
		this.userAuthRepo.save(superAdmin);
		this.userAuthRepo.save(u1);
		this.userAuthRepo.save(ua);
		this.userBasicRepo.save(new UserBasic(u1.getIduser(), "Jan","Nowak","M","Kraków"));
		this.userBasicRepo.save(new UserBasic(ua.getIduser(), "Piotr","Konicki","M","Kraków"));
		
		this.myFriendsRepo.save(new MyFriends(ua.getIduser(), u1.getIduser().toString()));
		this.myFriendsRepo.save(new MyFriends(u1.getIduser(), ua.getIduser().toString() ));
		
		//Place place = new Place("pp1", "Śnieżka", 5, 5, 5, "góry", "1232:654", 5, 3); 
		//this.PlaceRepo.save(place);
		
		
		Post p1 = new Post(Date.valueOf("2020-04-01"), ua.getIduser(), null,"EUPL0DLSREG0001", null, PostType.PLACE, "elo elo", "https://upload.wikimedia.org/wikipedia/commons/8/8a/%C5%9Anie%C5%BCka_z_zachodu.jpg", PostStatus.PUBLIC); 
		this.postRepo.save(p1);
		
		this.photoRepo.save(new Photo(p1.getPostid(), ua.getIduser(), "https://ocdn.eu/pulscms-transforms/1/3MCktkpTURBXy9mNzJlMWEyYWNmNTMwZGEyMzE3MzI0NGJhMWMzYjJhYy5qcGeRlQMAQc0ITs0Eqw", "#gory#natura"));
		this.photoRepo.save(new Photo(p1.getPostid(), ua.getIduser(), "https://www.radiowroclaw.pl/img/articles/26927/dTr5zvtDGJ.jpg", "#gory#natura"));
		this.photoRepo.save(new Photo(p1.getPostid(), ua.getIduser(), "https://www.noclegisudety.pl/wp-content/uploads/2011/03/HPIM2795.jpg", "#gory#natura"));
		
		
		
		this.commentRepo.save(new Comment(p1.getPostid(), u1.getIduser(),new Timestamp(System.currentTimeMillis()), "Świetne Zjęcie"));
		this.commentRepo.save(new Comment(p1.getPostid(), ua.getIduser(),new Timestamp(System.currentTimeMillis()+500), "Dzięki stary"));
		UsersAchievInProgress uaip1 = new UsersAchievInProgress(1001l, 1l, "1l", Date.valueOf("2020-03-01"));
		this.achInProgressRepo.save(uaip1);
		
	
		achievementDb();	
	}
	
	private void achievementDb() {
//		this.achievementRepo.deleteAll();
//		this.achievementPlaceRepo.deleteAll();
//		
//		Achievement ach1 = new Achievement("Zdobywaca Kontynentów", 300, "", "", "Odwieź wszystkie kontynenty");
//		Achievement ach2 = new Achievement("EuroTrip", 300, "", "", "");
//		Achievement ach3 = new Achievement("Jednoczący Króleswtowa", 300, "", "", "Odwiedź wszystkie królestwa w UK");
//		Achievement ach4 = new Achievement("Polska", 300, "", "", "Odwiedź wszystkie jedony w Polsce");
//		Achievement ach5 = new Achievement("Zdobywca sudetów", 300, "", "", "Odwiedź wszystkie jedony w Polsce");
//		Achievement ach6 = new Achievement("Grotołaz", 300, "", "", "");
//		Achievement ach7 = new Achievement("Wrocławski poszukiwacz", 300, "", "", "");
//		Achievement ach8 = new Achievement("Wrocławski mnich", 300, "", "", "Odwiedź najpopularniesze obiekty sakralne we Wrocławiu");
//		Achievement ach9 = new Achievement("Krakus", 300, "", "", "Odziedź wsztstkie punkry w Krakowie");
//		Achievement ach10 = new Achievement("Zdobywca kopuców", 300, "", "", "Odzwiedź wsztstkoe kopce w Krakowie");
//		this.achievementRepo.saveAll(Arrays.asList(ach1, ach2, ach3, ach4, ach5, ach6, ach7, ach8, ach9, ach10));
		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "EU0000000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "AN0000000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "AS0000000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "AZ0000000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "AU0000000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "AA0000000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach1.getIdachievement(), "AF0000000000000"));
		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUPL00000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUFR00000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUESP0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUITA0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUGER0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUENG0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUSCO0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUWAL0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach2.getIdachievement(), "EUIRP0000000000"));
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach3.getIdachievement(), "EUENG0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach3.getIdachievement(), "EUSCO0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach3.getIdachievement(), "EUWAL0000000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach3.getIdachievement(), "EUIRP0000000000"));
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0ZAC0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0LUB0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0DLS0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0WKP0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0OPL0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0SLA0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0MAP0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0POM0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0KUP0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0LOD0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0SWK0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0MAZ0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0POD0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0LBE0000000"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach4.getIdachievement(), "EUPL0PDK0000000"));
//		
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach5.getIdachievement(), "EUPL0DLSREG0001"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach5.getIdachievement(), "EUPL0DLSREG0002"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach5.getIdachievement(), "EUPL0DLSREG0003"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach5.getIdachievement(), "EUPL0DLSREG0004"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach6.getIdachievement(), "EUPL0DLSREG0005"));
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach7.getIdachievement(), "EUPL0DLSWRO0001"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach7.getIdachievement(), "EUPL0DLSWRO0002"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach7.getIdachievement(), "EUPL0DLSWRO0003"));
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach8.getIdachievement(), "EUPL0DLSWRO0002"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach8.getIdachievement(), "EUPL0DLSWRO0003"));
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach9.getIdachievement(), "EUPL0MAPKRK0001"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach9.getIdachievement(), "EUPL0MAPKRK0002"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach9.getIdachievement(), "EUPL0MAPKRK0003"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach9.getIdachievement(), "EUPL0MAPKRK0004"));
//		
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach10.getIdachievement(), "EUPL0MAPKRK0001"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach10.getIdachievement(), "EUPL0MAPKRK0002"));
//		this.achievementPlaceRepo.save(new AchievementPlaces(ach10.getIdachievement(), "EUPL0MAPKRK0003"));		
//
//		placeRepo.save(new Place("EU0000000000000", "Europa", 1000, 1000, 0, "continent", "", 100, 1));
//		placeRepo.save(new Place("AN0000000000000", "Ameryka Północna", 1000, 1000, 0, "continent", "", 100, 1));
//		placeRepo.save(new Place("AS0000000000000", "Ameryka Południowa", 1000, 1000, 0, "continent", "", 100, 1));
//		placeRepo.save(new Place("AZ0000000000000", "Azja", 1000, 1000, 0, "continent", "", 100, 1));
//		placeRepo.save(new Place("AU0000000000000", "Australia", 1000, 1000, 0, "continent", "", 100, 1));
//		placeRepo.save(new Place("AA0000000000000", "Antarktyda", 1000, 1000, 0, "continent", "", 100, 1));
//		placeRepo.save(new Place("AF0000000000000", "Afryka", 1000, 1000, 0, "continent", "", 100, 1));
//		
//		placeRepo.save(new Place("EUPL00000000000", "Polska", 1000, 1000, 0, "country", ach2.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUFR00000000000", "Francja", 1000, 1000, 0, "country", ach2.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUESP0000000000", "Hiszpania", 1000, 1000, 0, "country", ach2.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUITA0000000000", "Włochy", 1000, 1000, 0, "country", ach2.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUGER0000000000", "Niemcy", 1000, 1000, 0, "country", ach2.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUENG0000000000", "Anglia", 1000, 1000, 0, "country", ach2.getIdachievement().toString()+":"+ach3.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUSCO0000000000", "Szkocja", 1000, 1000, 0, "country", ach2.getIdachievement().toString()+":"+ach3.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUWAL0000000000", "Walia", 1000, 1000, 0, "country", ach2.getIdachievement().toString()+":"+ach3.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUIRP0000000000", "Irlandia Północna", 1000, 1000, 0, "country", ach2.getIdachievement().toString()+":"+ach3.getIdachievement().toString(), 100, 1));
//		
//		placeRepo.save(new Place("EUPL0ZAC0000000", "Zachodnio-pomorskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0LUB0000000", "Lubuskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0DLS0000000", "Dolnośląskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0WKP0000000", "Wielkopolskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0OPL0000000", "Opolskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0SLA0000000", "Śląskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0MAP0000000", "Małopolskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0POM0000000", "Pomorskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0KUP0000000", "Kujawsko-pomorskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0LOD0000000", "Łódzkie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0SWK0000000", "Świątokrzyskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0MAZ0000000", "Mazowieckie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0WMZ0000000", "Warmińsko-mazurskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0POD0000000", "Podlaskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0LBE0000000", "Lubelskie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		placeRepo.save(new Place("EUPL0PDK0000000", "Podkarpackie", 1000, 1000, 0, "region", ach4.getIdachievement().toString(), 100, 1));
//		
//		
//		placeRepo.save(new Place("EUPL0DLSREG0000", "REG", 1000, 1000, 0, "subregion", "", 100, 1));
//		placeRepo.save(new Place("EUPL0DLSWRO0000", "Wrocław", 1000, 1000, 0, "subregion", "", 100, 1));
//		placeRepo.save(new Place("EUPL0MAZWRS0000", "Warszawa", 1000, 1000, 0, "subregion", "", 100, 1));
//		placeRepo.save(new Place("EUPL0MAPKRK0000", "Kraków", 1000, 1000, 0, "subregion", "", 100, 1));
//		
//		
//		placeRepo.save(new Place("EUPL0DLSREG0001", "Śnieżka", 50.736198, 15.739875, 20, "szczyt", ach5.getIdachievement().toString(), 100, 3));
//		placeRepo.save(new Place("EUPL0DLSREG0002", "Śnieznik", 50.207760, 16.847915, 20, "szczyt", ach5.getIdachievement().toString(), 100, 3));
//		placeRepo.save(new Place("EUPL0DLSREG0003", "Ślęża", 50.865258, 16.708690, 20, "szczyt", ach5.getIdachievement().toString(), 100, 3));
//		placeRepo.save(new Place("EUPL0DLSREG0004", "Szczeliniec Wielki", 50.484874, 16.340968, 20, "szczyt", ach5.getIdachievement().toString(), 100, 3));
//		
//		placeRepo.save(new Place("EUPL0DLSREG0005", "Jaskinie Niedźwiedzia", 50.234488, 16.842555, 20, "jaskinia", ach5.getIdachievement().toString(), 100, 3));
//		
//		
//		placeRepo.save(new Place("EUPL0DLSWRO0001", "Rynek", 51.110304, 17.031540, 200, "vip", "999", 100, 1));
//		placeRepo.save(new Place("EUPL0DLSWRO0002", "Katedra", 51.114145, 17.046383, 200, "religia", "999:888", 100, 1));
//		placeRepo.save(new Place("EUPL0DLSWRO0003", "Wieża Widokowa Kościoła Garnizonowego", 51.111527, 17.029987, 20, "religia:punkt widokowy", "999:888", 100, 1));
//		
//	
//		placeRepo.save(new Place("EUPL0MAPKRK0001", "Kopitec Kościuszki", 50.054821, 19.893242, 20, "punkt widokowy", "666;777", 100, 1));
//		placeRepo.save(new Place("EUPL0MAPKRK0002", "Kopitec Krakusa", 50.038060, 19.958429, 20, "punkt widokowy", "666;777", 100, 1));
//		placeRepo.save(new Place("EUPL0MAPKRK0003", "Kopitec Wandy", 50.070199, 20.068088, 20, "punkt widokowy", "666;777", 100, 1));
//		placeRepo.save(new Place("EUPL0MAPKRK0004", "Wawel", 50.053981, 19.935143, 100, "historia", "666", 100, 1));
	}
}
