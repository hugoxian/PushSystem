package com.test.aop.jdk;

import com.test.aop.ForumService;

public class ForumServiceImpl implements ForumService {

	@Override
	public void removeForum(int forumId) throws Exception {
		System.out.println("模拟删除Forum记录:" + forumId);
		try {
			Thread.currentThread().sleep(200);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void removeTopic(int topicId) throws Exception {
		System.out.println("模拟删除Topic记录:" + topicId);
		try {
			Thread.currentThread().sleep(100);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
