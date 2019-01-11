package com.JVComponents.core;

import java.util.EventObject;

/**
 * @author DELL
 *
 *	���Ա仯�¼�����
 */
public class JVPropertyChangedEvent extends EventObject{

	/**
	 * ���л��汾�� 
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
