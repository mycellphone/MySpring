package com.cp.myaop;

public class TestAOP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MethodInvocation logTask = new MethodInvocation() {

			@Override
			public void invoke() {
				// TODO Auto-generated method stub
				System.out.println("log task start");
			}
			
		};
		HelloServiceImple helloServiceImple = new HelloServiceImple();
		
		BeforeAdvice beforeAdvice = new BeforeAdvice(helloServiceImple , logTask);
		
		HelloService helloServiceProxy = (HelloService) SimpleAOP.getProxy(helloServiceImple, beforeAdvice);
		
		helloServiceProxy.sayHello();
	}

}
