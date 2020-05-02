package com.chex.user.friends;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chex.db.InvitationRepo;
import com.chex.db.MyFriendsRepo;
import com.chex.db.UserAuthRepository;
import com.chex.db.UserDAO;
import com.chex.enums.PersonStatus;
import com.chex.model.User;

@Controller
public class SearchController {
	@Autowired 
	private UserAuthRepository userAuthDAO;
	@Autowired
	private InvitationRepo invitationDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MyFriendsRepo myFriendsDAO;
	
	@GetMapping(value = "/user/searchpeople")
	public String searchPeople(@RequestParam("search") String search, Principal principal, Model model) {
		List<User> foundUsers = userDAO.findAllContainPublicName(search);
		Long myId = userAuthDAO.findByUsername(principal.getName()).getUserid();
		List<FriendMiniView> mini_list = getSearchedMiniPersonList(foundUsers, myId);
		
		System.out.println(mini_list);
		model.addAttribute("seach_list", mini_list);
		return "user/friends/showsearched";
	}
	
	private List<FriendMiniView> getSearchedMiniPersonList(List<User> foundUsers, Long myId){
		List<FriendMiniView> mini_list = new ArrayList<>();

		for(User u : foundUsers) {
			FriendMiniView pm = new FriendMiniView();
			pm.setUserid(u.getUserid());
			pm.setPhoto(u.getProfilphoto());
			pm.setPublicname(u.getPublicname());
			
			if(invitationDAO.isUserIdAndInviters(myId, u.getUserid())){
				System.out.println(invitationDAO.isUserIdAndInviters(myId, u.getUserid()));
				pm.setPersonStatus("Invited");
			}
			else if(invitationDAO.isUserIdAndInviters(u.getUserid() , myId)){
				System.out.println(invitationDAO.isUserIdAndInviters(u.getUserid() , myId));
				pm.setPersonStatus("IsInvited");
			}
			else if(myFriendsDAO.isMyFriend(myId, Long.toString(u.getUserid()))) {
				pm.setPersonStatus("IsFriend");
			}else {
				pm.setPersonStatus("Unknown");
			}
			
			mini_list.add(pm);
		}
		return mini_list;
	}
}
