package com.test.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.test.aop.PerformanceMonitor;

/**
 * 
 * @author Hugo
 * 
 */
public class PerformaceHandler implements InvocationHandler {

	/**
	 * 为目标的业务类
	 */
	private Object target;

	public PerformaceHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName() + "."
				+ method.getName());
		Object obj = method.invoke(target, args);// ②通过反射方法调用目标业务类的业务方法
		PerformanceMonitor.end();
		return obj;

	}

}
