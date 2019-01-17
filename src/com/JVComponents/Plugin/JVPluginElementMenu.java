package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;
import java.util.*;

public class JVPluginElementMenu extends JVPluginElement {

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

	/**
	 * 得到label属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getLabel() throws JVException {
		//缺省label = name
		String str = (String)this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.label, str);
	}

	public JVConfigXMLAttribute getMnemonic() throws JVException {
		String str = JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic_value;
		return getAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic, str);
	}

	public JVPluginElementMenu(JVPluginExtension extension, Element element) throws JVException {
		super(extension, element);
		
		this.subMenus = new ArrayList<JVPluginElementMenu> ();
	}
	
	private ArrayList<JVPluginElementMenu> subMenus;
	
	public ArrayList<JVPluginElementMenu> getSubMenus() {
		return subMenus;
	}
	
	/**
	 * 父类读取后，需要根据子节点创建下级menu
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
			if(pluginElement instanceof JVPluginElementMenu) {
				subMenus.add((JVPluginElementMenu)pluginElement);
			}
		}
	}
	
	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginMenus.JVPluginMenu.menu;
	}

	
}
