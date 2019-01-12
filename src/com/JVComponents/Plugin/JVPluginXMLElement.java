package com.JVComponents.Plugin;

import org.dom4j.*;

import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVException;

public class JVPluginXMLElement extends JVComponent {
	
	/**
	 * ��Ӧ�Ľڵ�
	 */
	private Element element;
	public Element getElement() {
		return element;
	}
	
	/**
	 * @return
	 * 
	 * ���ڷ�װ�Ķ���
	 * 
	 */
	protected Object getPackagedObject(){
		return element;
	}
	
	/**
	 * �����ýڵ��XML�ļ�����
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
