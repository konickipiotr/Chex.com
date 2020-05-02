package com.chex.user.friends;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chex.db.InvitationRepo;
import com.chex.db.MyFriendsRepo;
import com.chex.db.user.UserAuthRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.Invitation;
import com.chex.model.MyFriends;

@RestController
public class MyFriendsController {

	@Autowired
	private MyFriendService myFriendService;
	
	@GetMapping("/api/myfriends/{userid}")
	public ResponseEntity<MyFriendViewWraper> getMyFriends(@PathVariable("userid") Long iduser){
		MyFriendViewWraper wraper = myFriendService.getMyFriends(iduser);
		if(!wraper.isEmpty())
			return new ResponseEntity<MyFriendViewWraper>(wraper, HttpStatus.OK);
		return new ResponseEntity<MyFriendViewWraper>(HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/api/friend")
//	public String userfriend(Principal principal, Model model) {
//		long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();
//		List<User> invitePerson = getUserList(myId);
//		List<FriendMiniView> inviteByPersonList = prepareMiniPerosnList(invitePerson);
//		
//		MyFriends fm =  myFriendsDAO.findById(myId).orElse(new MyFriends());
//		List<Long> myFriendsId = fm.toList();
//		List<User> myFriendsUsers =  userDAO.findAllById(myFriendsId);
//		List<FriendMiniView> miniListFriends = pereperMiniPersonListForFriends(myFriendsUsers);
//		
//		model.addAttribute("miniListFriends", miniListFriends);
//		model.addAttribute("inviteByPersonList", inviteByPersonList);
//		return "user/friends/showfriends";
//	}
//	
//	@GetMapping(value = "/user/friends/deletetFriend/{id}")
//	public String deletetFriend(@PathVariable("id") Long id, Principal principal) {
//		Long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();	
//		MyFriends mf = myFriendsDAO.findById(myId).orElse(null);
//		
//		if(mf != null) {
//			mf.removeFriend(id);
//			myFriendsDAO.save(mf);
//		}
//		return "redirect:/user/friend";
//	}
//	
//	private List<FriendMiniView> pereperMiniPersonListForFriends(List<User> myFriendsUsers){
//		if(myFriendsUsers.isEmpty()) return new ArrayList<>();
//		List<FriendMiniView> list = new ArrayList<>();
//		for(User u : myFriendsUsers) {
//			FriendMiniView mp = new FriendMiniView();
//			mp.setUserid(u.getUserid());
//			mp.setPhoto(u.getProfilphoto());
//			mp.setPublicname(u.getPublicname());
//			mp.setPersonStatus("IsFriend");
//			list.add(mp);
//		}
//		return list;		
//	}
//	
//	private List<FriendMiniView> prepareMiniPerosnList(List<User> invitePerson){
//		if(invitePerson.isEmpty()) return new ArrayList<>();
//		List<FriendMiniView> inviteByPersonList = new ArrayList<>();
//		for(User u : invitePerson) {
//			FriendMiniView miniP = new FriendMiniView();
//			miniP.setUserid(u.getUserid());
//			miniP.setPhoto(u.getProfilphoto());
//			miniP.setPublicname(u.getPublicname());
//			miniP.setPersonStatus("Invited");
//			inviteByPersonList.add(miniP);
//		}
//		return inviteByPersonList;
//	}
//	
//	private List<User> getUserList(Long myId) {
//		List<Invitation> invitation = invitationDAO.findByUserid(myId);
//		List<Long> id_list = new ArrayList<>();
//		for(Invitation i : invitation)
//			id_list.add(i.getInviters());
//		List<User> invitePerson = userDAO.findAllById(id_list);
//		
//		if(invitation == null || invitation.isEmpty())
//			return new ArrayList<>();
//		
//		return invitePerson;
//	}
}
