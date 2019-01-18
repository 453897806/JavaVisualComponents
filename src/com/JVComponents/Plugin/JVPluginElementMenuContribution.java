package com.JVComponents.Plugin;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConsts;
import com.JVComponents.core.JVException;

public class JVPluginElementMenuContribution extends JVPluginElement {
	
	public JVConfigXMLAttribute getLocationURI() throws JVException{
		//locationURI,缺省为主菜单
		return getAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.locationURI,
				JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.locationURI_MainMemu);
	}

	public JVPluginElementMenuContribution(JVPluginExtension extension, Element element) throws JVException {
		//用缺省组件名命名
		super(extension, element, element.attributeValue(JVConsts.componentDefualtName));
	}
	
	private ArrayList<JVPluginElementMenu> subMenus;
	private ArrayList<JVPluginElementToolbar> subToolbars;
	

	/**
	 * 与此菜单相关的子菜单
	 * 
	 * @return
	 */
	public ArrayList<JVPluginElementMenu> getSubMenus() {
		return subMenus;
	}
	
	/**
	 * 与此菜单相关的工具栏
	 * 
	 * @return
	 */
	public ArrayList<JVPluginElementToolbar> getSubToolbars() {
		return subToolbars;
	}

	/**
	 * 父类读取后，需要根据子节点创建下级menu或toolbar
	 * 
	 * @throws JVException 
	 */
	@Override
	public void createPluginElment() throws JVException {
		super.createPluginElment();
		
		//创建成员，避免下面使用时报null错误
		subMenus = new ArrayList<JVPluginElementMenu>();
		subToolbars = new ArrayList<JVPluginElementToolbar>();
		
		//创建子节点
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
			}else if(pluginElement instanceof JVPluginElementToolbar) {
				subToolbars.add((JVPluginElementToolbar)pluginElement);
			}
		}
	}
	
	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.menuContribution;
	}

}
