package com.chex.user.achievement;

import java.util.List;

public class AInProgressListWraper {
	private List<AchievementView> list;

	public AInProgressListWraper() {
	}

	public AInProgressListWraper(List<AchievementView> list) {
		this.list = list;
	}

	public List<AchievementView> getList() {
		return list;
	}

	public void setList(List<AchievementView> list) {
		this.list = list;
	}
	
	public boolean isEmpty() {
		if(list == null || list.isEmpty())
			return true;
		return false;
	}
}
