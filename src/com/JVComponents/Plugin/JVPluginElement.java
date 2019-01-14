package com.JVComponents.Plugin;


import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVException;

/**
 * 
 * 所有plugin中节点的基类
 * 
 * @author bob
 *
 */
public abstract class JVPluginElement extends JVConfigXMLElement {

	private JVPluginExtension extension;

	public JVPluginExtension getExtension() {
		return extension;
	}

	public JVPluginElement(JVPluginExtension extension)
			throws JVException {
		
		super(extension.getPluginFile(), extension.getElement().getElement());
		
		//成员
		this.extension = extension;
	}
	
	
	/**
	 *  
	 *  返回节点类型，子类需要继承
	 *  
	 * @return
	 */
	public abstract String getElementType();
}
