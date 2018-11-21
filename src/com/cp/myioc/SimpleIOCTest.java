package com.cp.myioc;

public class SimpleIOCTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
		SimpleIOC simpleIOC = new SimpleIOC(location);
		Wheel wheel = (Wheel) simpleIOC.getBean("wheel");
		System.out.println(wheel);
		Car car = (Car) simpleIOC.getBean("car");
		System.out.println(car);
		
		System.out.println("git hub");
	}

}
