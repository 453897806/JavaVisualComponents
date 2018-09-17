/**
 * 属性的基类
 */
package com.VisualJavaComponents.core;

import java.lang.reflect.*;

/**
 * @author root
 *	属性类，属性包括了
 *	1、数据类型
 *	2、属性值设置和属性值获取，通过反射机制，用其他函数控制属性值的读取和获取
 *	3、属性的缺省值
 */
public class VJCProperty {
	
	private Boolean booleanValue;
	private String stringValue;
	private Integer intValue;
	private Object objValue;
	
	private EPropertyDataType dataType;

	public EPropertyDataType getDataType() {
		return dataType;
	}

	public VJCProperty(EPropertyDataType dataType) {
		super();
		this.dataType = dataType;
		//缺省值
		this.booleanValue = false;
		this.stringValue = "";
		this.intValue = 0;
		this.objValue = null;
		
		this.invokeObject = null;
	}
	
	public Boolean getBooleanValue() throws InvocationTargetException,IllegalAccessException{
		if(getMethod != null) {
			booleanValue = (Boolean)getMethod.invoke(invokeObject, (Object[]) null);
		}
		
		return booleanValue;
	}

	public void setValue(Boolean value) {
		this.booleanValue = value;
	}

	public String getStringValue() throws InvocationTargetException,IllegalAccessException{
		if(getMethod != null) {
			stringValue = (String)getMethod.invoke(invokeObject, (Object[]) null);
		}
		
		return stringValue;
	}

	public void setValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Integer getIntValue() throws InvocationTargetException,IllegalAccessException{
		if(getMethod != null) {
			intValue = (Integer)getMethod.invoke(invokeObject, (Object[]) null);
		}
		return intValue;
	}

	public void setValue(Integer intValue) {
		this.intValue = intValue;
	}

	public Object getObjValue() throws InvocationTargetException,IllegalAccessException{
		if(getMethod != null) {
			objValue = getMethod.invoke(invokeObject, (Object[]) null);
		}
		return objValue;
	}

	public void setValue(Object objValue) {
		this.objValue = objValue;
	}
	
	//利用反射机制，控制读取和设置
	private Object invokeObject;
	private Method setMethod;
	private Method getMethod;
	
	/**
	 * 属性值设置时的外函数
	 * @return the setMethod
	 */
	public Method getSetMethod() {
		return setMethod;
	}

	/** 
	 * 属性值设置时的外函数
	 * @param setMethod the setMethod to set
	 */
	public void setSetMethod(Object obj, Method setMethod) {
		this.invokeObject = obj;
		this.setMethod = setMethod;
	}

	/**
	 * 属性值读取时的外函数
	 * @return the getMethod
	 */
	public Method getGetMethod() {
		return getMethod;
	}

	/**
	 * 属性值读取时的外函数
	 * @param getMethod the getMethod to set
	 */
	public void setGetMethod(Object obj, Method getMethod) {
		this.invokeObject = obj;
		this.getMethod = getMethod;
	}

}
