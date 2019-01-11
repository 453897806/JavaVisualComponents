package com.JVComponents.core;

import java.util.EventObject;

/**
 * @author DELL
 *
 *	属性变化事件对象
 */
public class JVPropertyChangedEvent extends EventObject{

	/**
	 * 序列化版本号 
	 */
	private static final long serialVersionUID = JVConsts.serialVersionUID;

	private Object oldValue;
	private Object newValue;
	

	/**
	 * @return the oldValue
	 */
	public Object getOldValue() {
		return oldValue;
	}


	/**
	 * @return the newValue
	 */
	public Object getNewValue() {
		return newValue;
	}


	public JVPropertyChangedEvent(JVProperty source, Object oldValue, Object newValue) {
		super(source);
		
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

}
