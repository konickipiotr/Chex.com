package com.chex.user.post.display;

import java.util.List;

public class PostListWraper {
	private List<PostView> posts;

	public PostListWraper() {
	}

	public PostListWraper(List<PostView> posts) {
		this.posts = posts;
	}

	public List<PostView> getPosts() {
		return posts;
	}

	public void setPosts(List<PostView> posts) {
		this.posts = posts;
	}
}
