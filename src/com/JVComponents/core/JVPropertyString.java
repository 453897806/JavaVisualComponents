package com.JVComponents.core;

/**
 * @author DELL
 * 
 * 字符串属性
 *
 */
public class JVPropertyString extends JVProperty {

	public JVPropertyString(JVComponent owner, Object defualtValue) throws JVException {
		super(owner, defualtValue);

		//检查缺省值的类型
		checkValueClasses("字符串属性", defualtValue);

	}

}
