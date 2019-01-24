package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVConsts;
import com.JVComponents.core.JVException;

public class JVPluginElementMenu extends JVPluginElement {

	private JVConfigXMLAttribute id;
	/**
	 * id属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getId() {
		return id;
	}

	private JVConfigXMLAttribute label;
	/**
	 * 得到label属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getLabel() {
		return label;
	}
	
	private JVConfigXMLAttribute mnemonic;
	/**
	 * 得到mnemonic属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getMnemonic() {
		return mnemonic;
	}

	@Override
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		id = getXMLAttribute(JVPluginConsts.JVPluginRoot.id, JVConsts.emptyString);
		label = getXMLAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.label, JVConsts.emptyString);
		mnemonic = getXMLAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic, JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic_value);
	}

	public JVPluginElementMenu(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}
	
	@Override
	public void matchPluginElement() throws JVException {
		//无需要匹配的对象			
	}

	
}
