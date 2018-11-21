package com.cp.myioc;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SimpleIOC {
	private HashMap<String , Object> beanMap = new HashMap<>();
	
	public SimpleIOC(String location) throws Exception {
		loadBeans(location);	//读取xml文件，加载bean
	}
	
	private void loadBeans(String location) throws Exception {
		File xmlFile = new File(location);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		
		for(int i=0; i< nodes.getLength() ; i++) {
			Node node = nodes.item(i);
			if(node instanceof Element) {
				Element ele = (Element)node;
				String id = ele.getAttribute("id");
				String className = ele.getAttribute("class");
				
				Class beanClass = null;
				try {
					beanClass = Class.forName(className);
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				Object bean = beanClass.newInstance();
				
				NodeList properties = ele.getElementsByTagName("property");
				for(int j =0 ; j < properties.getLength() ; j++) {
					Node propertyValue = properties.item(j);
					if(propertyValue instanceof Element) {
						Element property = (Element)propertyValue;
						String name = property.getAttribute("name");
						String value = property.getAttribute("value");
						
						Field declaredField = bean.getClass().getDeclaredField(name);
						declaredField.setAccessible(true);
						
						if(value !=null && value.length()>0 ) {
							declaredField.set(bean, value);
						}else {
							String ref = property.getAttribute("ref");
							if(ref == null || ref.length()==0)
								throw new IllegalArgumentException("ref config error");
							declaredField.set(bean, getBean(ref));
						}
						
						registerBean(id , bean);
					}
				}
			}
		}
	}
	
	public Object getBean(String name) {
		Object bean = beanMap.get(name);
		if (bean == null) {
			throw new IllegalArgumentException("there is no bean with name " + name);
		}
		
		return bean;
	}
	
	private void registerBean(String id , Object bean) {
		beanMap.put(id, bean);
	}
}
