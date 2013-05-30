package com.test.aop.cglib;

import com.test.aop.jdk.ForumServiceImpl;

public class TestForumService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CglibProxy proxy = new CglibProxy();
		ForumServiceImpl forumService = // ① 通过动态生成子类的方式创建代理对象
		(ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
		forumService.removeForum(10);
		forumService.removeTopic(1023);

	}

}
