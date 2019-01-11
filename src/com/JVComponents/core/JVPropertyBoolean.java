package com.JVComponents.core;

/**
 * @author DELL
 *
 *	布尔型属性
 *
 */
public class JVPropertyBoolean extends JVProperty {

	public JVPropertyBoolean(JVComponent owner, Object defualtValue) throws JVException {
		super(owner, defualtValue);

		//检查缺省值的类型
		Boolean tmp = false;
		checkValueClasses(tmp, defualtValue);

	}

}
