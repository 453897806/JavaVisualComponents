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
	
	/**
	 * 对应的plugin文件
	 * 
	 * @throws JVException
	 */
	public JVPluginXMLFile getPluginFile() throws JVException {
		// 检查
		if (!(getConfigXMLFile() instanceof JVPluginXMLFile)) {
			throw new JVException("不是Plugin的XML文件！", null);
		}

		return (JVPluginXMLFile) getConfigXMLFile();
	}

	public JVPluginElement(JVPluginXMLFile pluginXMLFile, JVPluginExtension extension, Element element) throws JVException {
		// 父类创建过程中自动读取了属性
		super(pluginXMLFile, element);

		// 成员
		this.extension = extension;
	}

	/**
	 * 根据节点内容进行对象配对
	 *  
	 */
	public abstract void matchPluginElement() throws JVException ;
}
