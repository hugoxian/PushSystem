package com.test.aop;

public class TestForumService {

	/**
	 * @param args
	 */
	// TODO Auto-generated method stub
	public static void main(String[] args) throws Exception {
		ForumService forumService = new ForumServiceImpl();
		forumService.removeForum(10);
		forumService.removeTopic(1012);
	}

}
