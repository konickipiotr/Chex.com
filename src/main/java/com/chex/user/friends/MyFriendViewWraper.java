package com.chex.user.friends;

import java.util.List;

public class MyFriendViewWraper {
	
	private List<FriendMiniView> list;

	public MyFriendViewWraper() {

	}

	public MyFriendViewWraper(List<FriendMiniView> list) {
		this.list = list;
	}

	public List<FriendMiniView> getList() {
		return list;
	}

	public void setList(List<FriendMiniView> list) {
		this.list = list;
	}
	
	public boolean isEmpty() {
		if(list == null || list.isEmpty())
			return true;
		return false;
	}
}
