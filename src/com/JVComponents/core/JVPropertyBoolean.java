package com.JVComponents.core;

/**
 * @author DELL
 *
 *	����������
 *
 */
public class JVPropertyBoolean extends JVProperty {

	public JVPropertyBoolean(JVComponent owner, Object defualtValue) throws JVException {
		super(owner, defualtValue);

		//���ȱʡֵ������
		Boolean tmp = false;
		checkValueClasses(tmp, defualtValue);

	}

}
