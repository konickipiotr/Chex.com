package com.chex.user.achievement;

import java.util.List;

import com.chex.model.achievement.Achievement;

public class AchievemenListWraper {
	private List<Achievement> list;

	public AchievemenListWraper() {
	}

	public AchievemenListWraper(List<Achievement> list) {
		this.list = list;
	}

	public List<Achievement> getList() {
		return list;
	}

	public void setList(List<Achievement> list) {
		this.list = list;
	}
	
	public boolean isEmpty() {
		if(list == null || list.isEmpty())
			return true;
		return false;
	}
	
}
