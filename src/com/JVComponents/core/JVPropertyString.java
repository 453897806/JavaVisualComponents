package com.JVComponents.core;

/**
 * @author DELL
 * 
 * �ַ�������
 *
 */
public class JVPropertyString extends JVProperty {

	public JVPropertyString(JVComponent owner, Object defualtValue) throws JVException {
		super(owner, defualtValue);

		//���ȱʡֵ������
		checkValueClasses("�ַ�������", defualtValue);

	}

}
