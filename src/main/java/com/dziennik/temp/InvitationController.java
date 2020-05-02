package com.chex.user.friends;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chex.db.InvitationRepo;
import com.chex.db.MyFriendsRepo;
import com.chex.db.UserAuthRepository;
import com.chex.model.Invitation;
import com.chex.model.MyFriends;

@Controller
@Transactional
public class InvitationController {

	@Autowired
	private InvitationRepo invitationDAO;
	@Autowired
	private UserAuthRepository userAuthDAO;
	@Autowired
	private MyFriendsRepo myFriendsDAO;
	
	@RequestMapping(value = "/user/friends/sendInv", method = RequestMethod.POST)
	public String sendInvitation(@RequestParam("id") Long id, Principal principal) {
		Long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();
		Invitation inv = new Invitation(id,myId);
		invitationDAO.save(inv);
		return "redirect:/user/friend";
	}
	
	@GetMapping(value = "/user/friends/acceptInv/{id}")
	public String acceptInvitation(@PathVariable("id") Long id, Principal principal) {
		Long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();
		MyFriends me = myFriendsDAO.findById(myId).orElse(null);
		MyFriends myfriend = myFriendsDAO.findById(id).orElse(null);
		if(me == null || myfriend == null)
			return "redirect:/user/friend";
		me.addFriendId(id);
		myfriend.addFriendId(myId);
		myFriendsDAO.save(me);
		myFriendsDAO.save(myfriend);	
		
		Invitation inv = invitationDAO.findByUserIdAndInviters(myId, id);
		invitationDAO.delete(inv);
		return "redirect:/user/friend";
	}
	
	@GetMapping(value = "/user/friends/rejectInv/{id}")
	public String rejecttInvitation(@PathVariable("id") Long id, Principal principal) {
		Long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();		
		Invitation inv = invitationDAO.findByUserIdAndInviters(myId, id);
		invitationDAO.delete(inv);
		return "redirect:/user/friend";
	}
	
	@GetMapping(value = "/user/friends/deletetInv/{id}")
	public String deletetInvitation(@PathVariable("id") Long id, Principal principal) {
		Long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();		
		Invitation inv = invitationDAO.findByUserIdAndInviters(id, myId);
		System.out.println(inv);
		invitationDAO.delete(inv);
		return "redirect:/user/friend";
	}
}
