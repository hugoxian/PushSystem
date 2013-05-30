package com.test.dp.factorymethod;

/**
 * 
 * @author Hugo
 * 
 */
public interface Document {
	void open();

	void save();

	void revert();

	void close();
}
