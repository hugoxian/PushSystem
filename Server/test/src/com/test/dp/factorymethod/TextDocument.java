package com.test.dp.factorymethod;

public class TextDocument implements Document {

	@Override 
    public void close() { 
        System.out.println(  "关闭txt文档" );  
    } 
 
    @Override 
    public void open() { 
        System.out.println(  "打开txt文档" );  
    } 
 
    @Override 
    public void revert() { 
 
        System.out.println(  "恢复txt文档" );  
    } 
 
    @Override 
    public void save() { 
        System.out.println(  "保存txt文档" );  
    } 

}
