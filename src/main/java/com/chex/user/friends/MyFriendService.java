package com.chex.user.friends;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chex.db.InvitationRepo;
import com.chex.db.MyFriendsRepo;
import com.chex.db.user.UserAuthRepo;
import com.chex.db.user.UserBasicRepo;
import com.chex.model.MyFriends;
import com.chex.model.user.UserBasic;
import com.chex.utils.IdUtils;

@Service
public class MyFriendService {

	private UserAuthRepo userAuthRepo;
	private InvitationRepo invitationRepo;
	private UserBasicRepo userBasicRepo;
	private MyFriendsRepo myFriendsRepo;
	
	public MyFriendService(UserAuthRepo userAuthRepo, InvitationRepo invitationRepo, UserBasicRepo userBasicRepo,
			MyFriendsRepo myFriendsRepo) {
		this.userAuthRepo = userAuthRepo;
		this.invitationRepo = invitationRepo;
		this.userBasicRepo = userBasicRepo;
		this.myFriendsRepo = myFriendsRepo;
	}
	
	public MyFriendViewWraper getMyFriends(Long iduser){
		MyFriends friends = myFriendsRepo.findById(iduser).get();
		System.out.println(friends.getUserFriendsId());
		List<Long> ids = IdUtils.splitToLongList(friends.getUserFriendsId());
		System.out.println(ids);
		List<UserBasic> friendsInfo = userBasicRepo.findAllById(ids);
		
		List<FriendMiniView> fmv = new ArrayList<>();
		for(UserBasic u : friendsInfo) {
			FriendMiniView fm = new FriendMiniView(u.getIduser(), u.getName(), "", "IsFriend"); //TODO photo
			fmv.add(fm);
		}
		return new MyFriendViewWraper(fmv);
	}
	
	

}
