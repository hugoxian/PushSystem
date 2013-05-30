package com.zcwl.ps.api;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static volatile List<String> rongqi = Collections
			.synchronizedList(new LinkedList<String>());

	public static void main(String arg[]) {

		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					rongqi.add("str" + i);
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					rongqi.remove("str" + i);
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					// Object[] ss = rongqi.toArray();
					// Runtime runtime = Runtime.getRuntime();
					// long totalMemory = runtime.totalMemory();
					// long useMemory = totalMemory - runtime.freeMemory();
					// System.out.println(useMemory);
					for (String s : rongqi) {
						System.out.println(s);
					}
				}
			}
		}.start();

	}

}
