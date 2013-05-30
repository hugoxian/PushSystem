package com.test.dom4j;

import java.io.ByteArrayInputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestDom4j {

	static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>1</id><type>LOGIN</type><message><appkey>sdfadf</appkey><imei>ds23sdf32</imei><userAgent>age</userAgent><lonlat>lon,lat</lonlat></message></xPush>";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(xml
				.getBytes("UTF-8")));
		Element rootElement = doc.getRootElement();
		Element element = rootElement.element("type");
		Element msgEle = rootElement.element("message").element("appkey");
		System.out.println(rootElement.attributeValue("version"));
		
	}

}