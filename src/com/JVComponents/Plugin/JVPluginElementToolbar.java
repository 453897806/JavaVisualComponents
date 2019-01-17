package com.JVComponents.Plugin;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementToolbar extends JVPluginElement {

	/**
	 * 得到id属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getId() throws JVException {
		//缺省id = 组件名称（容器内唯一）
		String id = (String)this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginRoot.id, id);
	}
	
	private ArrayList<JVPluginElementToolbarCommand> toolbarCommands;
	
	
	public ArrayList<JVPluginElementToolbarCommand> getToolbarCommands() {
		return toolbarCommands;
	}

	public JVPluginElementToolbar(JVPluginExtension extension, Element element) throws JVException {
		super(extension,element);
		toolbarCommands = new ArrayList<JVPluginElementToolbarCommand>(); 
	}	

	/**
	 * 父类读取后，需要根据子节点创建下级command
	 * 
	 * @throws JVException 
	 */
	@Override
	public void createPluginElment() throws JVException {
		super.createPluginElment();
		
		Iterator<Element> iter = getElement().elementIterator();
		Element element;
		JVPluginElement pluginElement;
		while(iter.hasNext()) {
			element = iter.next();
			
			//通过工厂创建，创建过程中读取内容
			pluginElement = JVPluginExtensionFactory.createPluginElement(getExtension(), element);
			//加入集合
			if(pluginElement instanceof JVPluginElementToolbarCommand) {
				toolbarCommands.add((JVPluginElementToolbarCommand)pluginElement);
			}
		}
	}
	
	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginMenus.JVPluginToolbar.toolbar;
	}

}
