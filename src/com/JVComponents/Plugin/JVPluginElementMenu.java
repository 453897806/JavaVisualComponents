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

	public JVPluginElementMenuCommand menuCommand;
	
	/**
	 *  menu对应的command
	 * @return
	 */
	public JVPluginElementMenuCommand getMenuCommand() {
		return menuCommand;
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
		//用id命名
		super(extension, element, element.attributeValue(JVPluginConsts.JVPluginRoot.id));
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
		
		//创建成员，避免下面使用时报null错误
		this.subMenus = new ArrayList<JVPluginElementMenu> ();
		
		//根据子节点创建下级menu
		Iterator<Element> iter = getElement().elementIterator();
		Element element;
		JVPluginElement pluginElement;
		while(iter.hasNext()) {
			element = iter.next();
			//检查节点名称，由于menuCommand的节点名称仍然是command，所以需要作调整
			String str = element.getName();
			if(str.equals(JVPluginConsts.JVPluginCommands.JVPluginCommand.command)){
				element.setName(JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand);
			}
			
			//通过工厂创建，创建过程中读取内容
			pluginElement = JVPluginExtensionFactory.createPluginElement(getExtension(), element);
			//如果是菜单则加入子菜单集合
			if(pluginElement instanceof JVPluginElementMenu) {
				subMenus.add((JVPluginElementMenu)pluginElement);
			}else if(pluginElement instanceof JVPluginElementMenuCommand){
				menuCommand = (JVPluginElementMenuCommand)pluginElement;				
			}
		}
	}
	
	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginMenus.JVPluginMenu.menu;
	}

	
}
