package com.test.aop;

/**
 * 监控器
 * 
 * @author Hugo
 * 
 */
public class PerformanceMonitor {
	// 通过一个ThreadLocal保存线程相关的性能监视信息
	private static ThreadLocal<MethodPerformace> performaceRecord = new ThreadLocal<MethodPerformace>();

	public static void begin(String method) {
		System.out.println("begin monitor...");
		MethodPerformace mp = new MethodPerformace(method);
		performaceRecord.set(mp);
	}

	public static void end() {
		System.out.println("end monitor...");
		MethodPerformace mp = performaceRecord.get();
		mp.printPerformace(); // 打印出业务方法性能监视的信息
	}

}
