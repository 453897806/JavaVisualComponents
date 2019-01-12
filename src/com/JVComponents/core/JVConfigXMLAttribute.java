package com.JVComponents.core;

import org.dom4j.Attribute;

public class JVConfigXMLAttribute extends JVAbstractComponent {

	/**
	 *	���������ԵĽڵ� 
	 */
	private JVConfigXMLElement element;
	public JVConfigXMLElement getElement() {
		return element;
	}
	
	/**
	 * ��Ӧ�����Զ���
	 */
	private Attribute attribute;
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @return
	 * 
	 * ���ڷ�װ�Ķ���
	 * 
	 */
	protected Object getPackagedObject(){
		return attribute;
	}
	
	/**
	 * ����ֵ
	 */
	private JVPropertyString value;
	public JVPropertyString getValue() {
		return value;
	}

	public JVConfigXMLAttribute(JVConfigXMLElement element, Attribute attribute) throws JVException {
		super(attribute.getName());
		
		//����ֵ
		value = new JVPropertyString(this, attribute.getValue());

		//��Ա
		this.attribute = attribute;
		this.element = element;	
	}

}
