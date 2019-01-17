/**
 * 
 */
package com.JVComponents.core;

import java.util.*;

/**
 * @author JVC
 *
 *         所有属性基类
 */
public abstract class JVProperty {

	/**
	 * 属性变化侦听事件
	 * 
	 * 允许多个侦听事件
	 */
	private HashSet<JVPropertyChangedListener> valueChangedListeners;

	public void removeValueListener(JVPropertyChangedListener listener) {
		valueChangedListeners.remove(listener);
	}

	public void addListener(JVPropertyChangedListener listener) {
		if (!valueChangedListeners.contains(listener)) {
			valueChangedListeners.add(listener);
		}
	}

	/**
	 * 获取属性值的接口
	 */
	private JVPropertyGetHandler valueGetHandler;

	public JVPropertyGetHandler getValueGetHandler() {
		return valueGetHandler;
	}

	public void setValueGetHandler(JVPropertyGetHandler handler) {
		this.valueGetHandler = handler;
	}

	/**
	 * 设置属性值的接口
	 */
	private JVPropertySetHandler valueSetHandler;

	public JVPropertySetHandler getValueSetHandler() {
		return valueSetHandler;
	}

	public void setValueSetHandler(JVPropertySetHandler handler) {
		this.valueSetHandler = handler;
	}

	private Object value;

	/**
	 * 返回属性的值，值是一个Object对象，需要进行类型的强制转换
	 * 
	 * @return the value 属性的值
	 * 
	 */
	public Object getValue() throws JVException {
		Object newValue;
		// 如果属性值获取接口不为空，则利用接口完成
		if (valueGetHandler != null) {
			newValue = valueGetHandler.getPropertyValue(this);

			// 检查返回值的类型正确性
			checkValueClasses(value, newValue);

			// 赋值给value
			value = newValue;
		}
		return value;
	}

	public void setValue(Object value) throws JVException {
		if(!readOnly) {
			throw new JVException("只读属性不允许修改值！", null);
		}
		
		if (this.value != value) {
			// 校验属性值的类型
			checkValueClasses(this.value, value);

			// 属性值变化时处理事件
			JVPropertyChangedEvent event = new JVPropertyChangedEvent(this, this.value, value);
			Iterator<JVPropertyChangedListener> iter = valueChangedListeners.iterator();
			JVPropertyChangedListener listener;
			while (iter.hasNext()) {
				listener = iter.next();
				listener.handleEvent(event);
			}

			// 赋值
			this.value = value;

			// 如果属性值设置接口不为空，再利用接口进一步处理
			if (valueSetHandler != null) {
				valueSetHandler.setPropertyValue(this, value);
			}
			;
		}
	}

	/**
	 * 校验新赋值的类型是否正确
	 * 
	 * @param oldvalue 当前值
	 * 
	 * @param value    新赋值
	 * @throws JVException
	 * 
	 */
	protected void checkValueClasses(Object oldvalue, Object value) throws JVException {
		// 类型不相同则触发异常
		if (value.getClass() != oldvalue.getClass()) {
			throw new JVException("属性赋值的类型不正确！", null);
		}

	}

	/**
	 * 属性归属的组件
	 */
	private JVComponent owner;

	public JVComponent getOwner() {
		return owner;
	}

	/**
	 * @param defualtValue 缺省值
	 * 
	 * @param owner        属性归属的组件
	 * 
	 */
	public JVProperty(JVComponent owner, Object defualtValue) {
		super();
		//缺省不是只读
		readOnly = false;
		
		// 缺省值
		this.value = defualtValue;

		// 归属的组件
		this.owner = owner;

		// 属性变化事件侦听
		valueChangedListeners = new HashSet<JVPropertyChangedListener>();

		// 属性获取和设置的接口
		valueGetHandler = null;
		valueSetHandler = null;
	}

	/**
	 * 属性的只读性
	 */
	private Boolean readOnly;

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

}
