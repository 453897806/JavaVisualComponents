package com.VisualJavaComponents.core;

import org.dom4j.*;

import com.JVComponents.Plugin.JVPluginXMLElement;
import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVException;

public class JVPluginXMLAtribute extends JVComponent {
	
	/**
	 *	���������ԵĽڵ� 
	 */
	private JVPluginXMLElement element;
	public JVPluginXMLElement getElement() {
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
	

	public JVPluginXMLAtribute(JVPluginXMLElement element, Attribute attribute) throws JVException {
		super(attribute.toString());
		
		//
		this.attribute = attribute;
		this.element = element;		
	}

}
