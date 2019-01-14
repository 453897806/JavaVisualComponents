package com.JVComponents.Plugin;

import java.util.*;
import org.dom4j.*;

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

	private HashSet<JVPluginElementCommand> commands; 
	private HashSet<JVPluginElementCategory> categorys;
	
	
	public JVPluginExtensionCommands(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container, element);
		
		commands = new HashSet<JVPluginElementCommand> (); 
	}
	
	@Override
	public String getExtensionPoint() {
		return JVPluginConsts.extensionCommands;
	}

	/**
	 * 
	 * 根据节点读取扩展对象的内容并检查，子类需要继承以读取不同类型的数据
	 * 
	 */
	@Override
	public void readPluginExtension() throws JVException {
		super.readPluginExtension();
		
		//读取子节点
		Iterator<Element> iter = getElement().getElement().elementIterator();
		Element element;
		while(iter.hasNext()) {
			element = iter.next();
			
		}		
	}
}
