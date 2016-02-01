package com.zhangyingwei.simpleutils.spring;

public interface ISpringUtil {
	public void loadContext(String path);
	public <T> Object getBean(Class<T> c);
	public Object getBean(String beanName);
}
