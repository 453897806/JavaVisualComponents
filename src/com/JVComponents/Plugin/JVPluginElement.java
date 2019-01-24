package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

/**
 * 
 * 所有plugin中节点的基类
 * 
 * @author bob
 *
 */
public abstract class JVPluginElement extends JVConfigXMLElement {

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

	public JVPluginElement(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}

	/**
	 * 根据节点内容进行对象配对
	 *  
	 */
	public abstract void matchPluginElement() throws JVException ;
}
