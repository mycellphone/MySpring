package com.cp.myaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BeforeAdvice implements InvocationHandler {
	
	private Object bean;
	private MethodInvocation methodInvocation;
	
	public BeforeAdvice(Object bean , MethodInvocation methodInvocation) {
		this.bean = bean;
		this.methodInvocation = methodInvocation;	//额外新添的方法注入进去
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		methodInvocation.invoke();
		return method.invoke(bean, args);
	}

}
