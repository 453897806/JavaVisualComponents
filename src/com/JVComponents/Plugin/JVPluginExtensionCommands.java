package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;

/**
 * Commands扩展
 * 
 * @author DELL
 *
 */
public class JVPluginExtensionCommands extends JVPluginExtension {

	public JVPluginExtensionCommands(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container, element);
		
	}
	
	@Override
	public String getExtensionPoint() {
		return JVPluginConsts.extensionCommands;
	}

	/**
	 * 
	 * 创建扩展对象的机构体，子类需要继承以创建不同类型的结构体
	 * 
	 * @throws JVException
	 * 
	 */
	public void createPluginExtension() throws JVException {
		super.createPluginExtension();
		
		
	}
	
	/**
	 * 
	 * 根据节点读取扩展对象的内容并检查，子类需要继承以读取不同类型的数据
	 * 
	 */
	public void readPluginExtension() throws JVException {
		super.readPluginExtension();
		
		
	}
}
