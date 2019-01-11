/**
 * 
 */
package com.JVComponents.core;


import java.util.*;

/**
 * @author JVC
 *
 *	�������Ի���
 */
public abstract class JVProperty {
	
	
	/**
	 * ���Ա仯�����¼� 
	 * 
	 * �����������¼�
	 */
	private HashSet<JVPropertyChangedListener> valueChangedListeners;
	public void removeValueListener(JVPropertyChangedListener listener) {
		valueChangedListeners.remove(listener);
	}
	public void addListener(JVPropertyChangedListener listener) {
		if(!valueChangedListeners.contains(listener)) {
			valueChangedListeners.add(listener);
		}
	}

	/**
	 * ��ȡ����ֵ�Ľӿ�
	 */
	private JVPropertyGetHandle valueGetHandle;
	public JVPropertyGetHandle getValueGetHandle() {
		return valueGetHandle;
	}
	public void setGetValueHandle(JVPropertyGetHandle handle) {
		this.valueGetHandle = handle;
	}
	
	/**
	 * ��������ֵ�Ľӿ�
	 */
	private JVPropertySetHandle valueSetHandle;
	public JVPropertySetHandle getValueSetHandle() {
		return valueSetHandle;
	}
	public void setValueSetHandle(JVPropertySetHandle handle) {
		this.valueSetHandle = handle;
	}

	/**
	 * @return the value
	 * 
	 * ���Ե�ֵ
	 * 
	 */
	private Object value;
	public Object getValue() throws JVException {
		Object newValue;
		//�������ֵ��ȡ�ӿڲ�Ϊ�գ������ýӿ����
		if(valueGetHandle != null) {
			newValue = valueGetHandle.getPropertyValue(this);
			
			//��鷵��ֵ��������ȷ��
			checkValueClasses(value,newValue);
			
			//��ֵ��value
			value = newValue;
		}
		return value;
	}
	
	
	public void setValue(Object value) throws JVException {
		if(this.value != value) {
			//У������ֵ������
			checkValueClasses(this.value, value);
			
			//����ֵ�仯ʱ�����¼�
			JVPropertyChangedEvent event = new JVPropertyChangedEvent(this, this.value, value);
			Iterator<JVPropertyChangedListener> iter = valueChangedListeners.iterator();
			JVPropertyChangedListener listener;
			while(iter.hasNext()) {
				listener = iter.next();
				listener.handleEvent(event);
			}
			
			//��ֵ			
			this.value = value;
			
			//�������ֵ���ýӿڲ�Ϊ�գ������ýӿڽ�һ������
			if(valueSetHandle != null) {
				valueSetHandle.setPropertyValue(this, value);				
			};			
		}
	}

	/**
	 * У���¸�ֵ�������Ƿ���ȷ
	 * 
	 * @param oldvalue
	 * ��ǰֵ
	 * 
	 * @param value
	 * �¸�ֵ
	 * @throws JVException 
	 * 
	 */
	protected void checkValueClasses(Object oldvalue, Object value) throws JVException {
		//���Ͳ���ͬ�򴥷��쳣
		if(value.getClass() != oldvalue.getClass()) {
			throw new JVException("���Ը�ֵ�����Ͳ���ȷ��",null);
		}
		
	}
	
    /**
     * ���Թ��������
     */
    private JVComponent owner;
	public JVComponent getOwner() {
		return owner;
	}
	
	/**
	 * @param defualtValue
	 * ȱʡֵ
	 * 
	 * @param owner
	 * ���Թ��������
	 * 
	 */
	public JVProperty(JVComponent owner, Object defualtValue) {
		super();
		//ȱʡֵ
		this.value = defualtValue;
		
		//���������
		this.owner = owner;
		
		//���Ա仯�¼�����
		valueChangedListeners = new HashSet<JVPropertyChangedListener>(); 
		
		//���Ի�ȡ�����õĽӿ�
		valueGetHandle = null;
		valueSetHandle = null;
	}

}
