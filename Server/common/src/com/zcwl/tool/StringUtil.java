package com.zcwl.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * 工具类
 * 
 * @author Hugo
 * 
 */
public class StringUtil {

	/**
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String generateUuid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 读取指定路径文本信息
	 * 
	 * @param filePath
	 * @param encoding
	 * @param lineFeed
	 * @return
	 */
	public static String readFile2Text(String filePath, String encoding) {
		if (filePath == null) {
			return null;
		}

		StringBuffer result = new StringBuffer();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String text = null;
				while ((text = bufferedReader.readLine()) != null) {
					result.append(text);
					result.append("\r\n");
				}
				read.close();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}

	/**
	 * 是否空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static void main(String arg[]) {
		System.out.println(readFile2Text("c:/aa.txt", "utf-8"));
	}
}
