package com.JVComponents.Plugin;

import org.dom4j.*;

import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVException;

public class JVPluginXMLElement extends JVComponent {
	
	/**
	 * 对应的节点
	 */
	private Element element;
	public Element getElement() {
		return element;
	}
	
	/**
	 * @return
	 * 
	 * 用于封装的对象
	 * 
	 */
	protected Object getPackagedObject(){
		return element;
	}
	
	/**
	 * 包含该节点的XML文件对象
	 */
	private JVPluginXMLFile pluginXMLFile;
	public JVPluginXMLFile getPluginXMLFile() {
		return pluginXMLFile;
	}

	public JVPluginXMLElement(JVPluginXMLFile pluginXMLFile, Element element) throws JVException {
		super(element.toString());
		
		this.pluginXMLFile = pluginXMLFile;
		this.element = element;
	}
}
