/**
 * 属性的基类
 */
package com.VisualJavaComponents.core;

/**
 * @author root
 *	属性基类，属性包括了
 *	1、数据类型，由不同子类继承后实现
 *	2、属性值设置和属性值获取，通过反射机制，用其他函数控制属性值的读取和获取
 *	3、属性的缺省值
 */
public class VJCBaseProperty {
	
	private Boolean booleanValue;
	private String stringValue;
	private Integer intValue;
	private Object objValue;
	
	private EPropertyDataType dataType;

	public EPropertyDataType getDataType() {
		return dataType;
	}

	public VJCBaseProperty(EPropertyDataType dataType) {
		super();
		this.dataType = dataType;
	}
	
	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setValue(Boolean value) {
		this.booleanValue = value;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setValue(Integer intValue) {
		this.intValue = intValue;
	}

	public Object getObjValue() {
		return objValue;
	}

	public void setValue(Object objValue) {
		this.objValue = objValue;
	}
	
	
}
