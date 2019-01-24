package com.JVComponents.Plugin;

import org.dom4j.Element;

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

	public JVPluginElement(JVPluginExtension extension, Element element) throws JVException {
		// 父类创建过程中自动读取了属性
		super(extension.getPluginFile(), element);

		// 成员
		this.extension = extension;
	}

	/**
	 * 
	 * 返回节点类型，子类需要继承
	 * 
	 * @return
	 */
	public abstract String getElementType();

	/**
	 * 根据节点内容进行对象配对
	 *  
	 */
	public abstract void matchPluginElement() throws JVException ;
}
