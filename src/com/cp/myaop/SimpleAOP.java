package com.cp.myaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class SimpleAOP {
	public static Object getProxy(Object bean , InvocationHandler advice) {
		return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader()	, bean.getClass().getInterfaces() , advice);
	}
}
