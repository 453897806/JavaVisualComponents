package com.JVComponents.core;

import org.dom4j.Attribute;

public class JVConfigXMLAttribute extends JVAbstractComponent {

	/**
	 * 包含此属性的节点
	 */
	private JVConfigXMLElement element;

	public JVConfigXMLElement getElement() {
		return element;
	}

	/**
	 * 对应的属性对象
	 */
	private Attribute attribute;

	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @return
	 * 
	 * 		用于封装的对象
	 * 
	 */
	protected Object getPackagedObject() {
		return attribute;
	}

	/**
	 * 属性值
	 */
	private JVPropertyString value;

	public JVPropertyString getValue() {
		return value;
	}

	/**
	 * 属性值的读写都是针对XML节点的attribute进行
	 * 
	 * @author bob
	 *
	 */
	private class JVAttributeValueHandler implements JVPropertyGetHandler,JVPropertySetHandler {

		private JVConfigXMLAttribute owner;

		
		/**
		 * @param property
		 * @param value
		 * @return 
		 *   属性赋值后的新值，缺省等于传入的值
		 */
		@Override
		public void setPropertyValue(JVProperty property, Object value) {
			//设置节点属性值
			this.owner.attribute.setValue((String)value);
		}
		

		@Override
		public Object getPropertyValue(JVProperty property) {
			//返回节点属性值
			return this.owner.attribute.getValue();
		}


		public JVAttributeValueHandler(JVConfigXMLAttribute owner) {
			super();
			this.owner = owner;
		}
	}

	private void createByAttribute(JVConfigXMLElement element, Attribute attribute) throws JVException {
		// 成员
		this.element = element;

		// 根据名称取得属性对象
		if(attribute == null) {
			throw new JVException("节点没有属性对象!",null);
		}
		this.attribute = attribute;

		// 属性值
		this.value = new JVPropertyString(this, this.attribute.getValue());
		// 增加侦听，当属性值变化时，需要同时写XML的属性值
		JVAttributeValueHandler valueHandler = new JVAttributeValueHandler(this);
		this.value.setValueGetHandler(valueHandler);
		this.value.setValueSetHandler(valueHandler);
	}

	/**
	 * 根据属性名称创建一个属性对象
	 * 
	 * @param element
	 * 		所属的节点对象
	 * @param attributeName
	 * 		属性名称
	 * @param attributeValue
	 * 		缺省值
	 * @throws JVException
	 */
	public JVConfigXMLAttribute(JVConfigXMLElement element, String attributeName, String attributeValue) throws JVException {
		super(attributeName);

		//查找属性，如果不存在则创建一个空字符串的属性
		Attribute attribute = element.getElement().attribute(attributeName);
		if (attribute == null) {
			//创建
			element.getElement().addAttribute(attributeName, attributeValue);
			//重新获取一次
			attribute = element.getElement().attribute(attributeName);
		}
		//创建属性
		createByAttribute(element, attribute);
	}

	public JVConfigXMLAttribute(JVConfigXMLElement element, Attribute attribute) throws JVException {
		super(attribute.getName());
		//创建属性
		createByAttribute(element, attribute);
	}

}
