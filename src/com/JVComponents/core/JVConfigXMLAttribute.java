package com.JVComponents.core;

import org.dom4j.Attribute;

public class JVConfigXMLAttribute extends JVAbstractComponent {

	/**
	 *	包含此属性的节点 
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
	 * 用于封装的对象
	 * 
	 */
	protected Object getPackagedObject(){
		return attribute;
	}
	
	/**
	 * 属性值
	 */
	private JVPropertyString value;
	public JVPropertyString getValue() {
		return value;
	}

	public JVConfigXMLAttribute(JVConfigXMLElement element, Attribute attribute) throws JVException {
		super(attribute.getName());
		
		//属性值
		value = new JVPropertyString(this, attribute.getValue());

		//成员
		this.attribute = attribute;
		this.element = element;	
	}

}
