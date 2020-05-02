package com.chex.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class MyFriends {
	@Id
	private Long userid;
	@Lob
	private String userFriendsId;
	
	public MyFriends() {
	}

	public MyFriends(Long userid, String userFriendsId) {
		this.userid = userid;
		this.userFriendsId = userFriendsId;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserFriendsId() {
		return userFriendsId;
	}

	public void setUserFriendsId(String userFriendsId) {
		this.userFriendsId = userFriendsId;
	}

	@Override
	public String toString() {
		return "MyFriends [userid=" + userid + ", userFriendsId=" + userFriendsId + "]";
	}

	public List<Long> toList(){
		List<Long> list = new ArrayList<>();
		if(userFriendsId == null || userFriendsId.isBlank())return list;
		String f_array[] = userFriendsId.split(":");
		
		for(String s : f_array) {
			list.add(Long.parseLong(s));
		}
		return list;		
	}
	
	public void addFriendId(Long friendId) {
		if(userFriendsId == null || userFriendsId.isBlank()) {
			userFriendsId = Long.toString(friendId);
		}
		else {
			userFriendsId += ":" + Long.toString(friendId);
		}
		
	}
	
	public void removeFriend(Long friendId) {
		List<Long> list = this.toList();
		if(!list.contains(friendId)) {
			return;
		}
		String sfriendId;
		
		if(list.get(0).equals(friendId))
			sfriendId = Long.toString(friendId);
		else
			sfriendId = ":" + Long.toString(friendId);
		
		this.userFriendsId = this.userFriendsId.replace(sfriendId, "");
	}
}
