package com.JVComponents.core;

public class JVPropertyInteger extends JVProperty {

	public JVPropertyInteger(JVComponent owner, Object defualtValue) throws JVException {
		super(owner, defualtValue);
		
		//���ȱʡֵ������
		Integer tmp = 1;
		checkValueClasses(tmp, defualtValue);

	}

}
