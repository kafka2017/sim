package com.amwell.demo;

public class StaticDemo {
	
	static{
		_i = 20;
	}
	
	static {
		_i = 30;
	}
	
	public static int _i = 10;
	
	public static volatile StaticDemo instance = null;
	
	static{
		instance = new StaticDemo();
	}
	

	public static void main(String[] args) {
		System.err.println(_i);
	}

}
