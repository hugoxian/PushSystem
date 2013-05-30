package com.test.aop;

/**
 * 
 * @author Hugo
 * 
 */
public interface ForumService {
	/**
	 * 
	 * @throws Exception
	 */
	public void removeTopic(int topicId) throws Exception;

	public void removeForum(int forumId) throws Exception;
}
