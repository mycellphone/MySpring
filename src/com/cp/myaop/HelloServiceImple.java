package com.cp.myaop;

interface HelloService{
	void sayHello();
}

public class HelloServiceImple implements HelloService{

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("hello world");
	}
	
}
