package com.zhangyingwei.simpleutils.spring;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil implements ISpringUtil{
	Logger logger = Logger.getLogger(SpringUtil.class);
	/**
	 * º”‘ÿ¬∑æ∂
	 */
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	private ApplicationContext context;
	
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	@Override
	public void loadContext(String path) {
		if(StringUtils.isEmpty(path)){
			this.setPath("classpath:applicationContext.xml");
		}else{
			this.setPath(path);
		}
		this.setContext(new ClassPathXmlApplicationContext(this.getPath()));
	}

	@Override
	public <T> Object getBean(Class<T> c) {
		if(this.getContext()==null){
			logger.info("context is null,please load first");
			return null;
		}else{
			return this.getContext().getBean(c);
		}
	}
	@Override
	public Object getBean(String beanName) {
		if(this.getContext()==null){
			logger.info("context is null,please load first");
			return null;
		}else{
			return this.getContext().getBean(beanName);
		}
	}
}
