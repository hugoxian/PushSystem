package com.test.aop;

public class ForumServiceImpl implements ForumService {

	@Override
	public void removeForum(int forumId) throws Exception {
		// 开始性能监视
		PerformanceMonitor
				.begin("com.baobaotao.proxy.ForumServiceImpl.removeForum");
		System.out.println("模拟删除Forum记录:" + forumId);
		try {
			Thread.currentThread().sleep(2000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 结束监视、并给出性能报告信息
		PerformanceMonitor.end();

	}

	@Override
	public void removeTopic(int topicId) throws Exception {
		PerformanceMonitor
				.begin("com.baobaotao.proxy.ForumServiceImpl.removeTopic");
		System.out.println("模拟删除Topic记录:" + topicId);
		try {
			Thread.currentThread().sleep(1000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 结束监视、并给出性能报告信息
		PerformanceMonitor.end();

	}

}
