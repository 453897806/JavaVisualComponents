package com.JVComponents.core;

public class JVPropertyInteger extends JVProperty {

	public JVPropertyInteger(JVComponent owner, Object defualtValue) throws JVException {
		super(owner, defualtValue);
		
		//检查缺省值的类型
		Integer tmp = 1;
		checkValueClasses(tmp, defualtValue);

	}

}
