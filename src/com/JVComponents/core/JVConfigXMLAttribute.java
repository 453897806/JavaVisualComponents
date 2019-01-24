package com.JVComponents.core;

import org.dom4j.*;

public class JVConfigXMLAttribute extends JVAbstractComponent {
	
	private Attribute attribute;
	
	/**
	 * @return
	 * 
	 * 用于封装的对象
	 * 
	 */
	protected Object getPackagedObject(){
		return attribute;
	}
	
	private JVConfigXMLElement xmlElement;
	
	public JVConfigXMLElement getXmlElement() {
		return xmlElement;
	}

	/**
	 * 属性值
	 */
	private JVPropertyString value;

	public JVPropertyString getValue() {
		return value;
	}

	/**
	 * 根据属性名称创建一个属性对象
	 * 
	 * @param attributeName
	 * 		属性名称
	 * @param attributeValue
	 * 		缺省值
	 * @throws JVException
	 */
	public JVConfigXMLAttribute(JVConfigXMLElement xmlElement, Attribute attribute) throws JVException {
		super(attribute.getName());
		
		this.xmlElement = xmlElement;
		this.attribute = attribute;

		//创建属性
		this.value = new JVPropertyString(this, attribute.getValue());
	}
}
