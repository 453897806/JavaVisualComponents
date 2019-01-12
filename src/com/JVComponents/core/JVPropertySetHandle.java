package com.JVComponents.core;

/**
 * @author DELL
 *
 * 属性值设置方法
 */
public interface JVPropertySetHandle {

	/**
	 * @param property
	 * @param value
	 * @return 
	 *   属性赋值后的新值，缺省等于传入的值
	 */
	public void setPropertyValue(JVProperty property, Object value);
}
