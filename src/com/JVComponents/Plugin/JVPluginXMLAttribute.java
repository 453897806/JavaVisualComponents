package com.JVComponents.Plugin;

import org.dom4j.*;

import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVException;

public class JVPluginXMLAtribute extends JVComponent {
	
	/**
	 *	包含此属性的节点 
	 */
	private JVPluginXMLElement element;
	public JVPluginXMLElement getElement() {
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
	

	public JVPluginXMLAtribute(JVPluginXMLElement element, Attribute attribute) throws JVException {
		super(attribute.toString());
		
		//
		this.attribute = attribute;
		this.element = element;		
	}

}
