package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVConsts;
import com.JVComponents.core.JVException;

public class JVPluginElementToolbar extends JVPluginElement {

	private JVConfigXMLAttribute id;
	/**
	 * id属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getId() {
		return id;
	}
	
	public JVPluginElementToolbar(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}	
	
	@Override
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		id = getXMLAttribute(JVPluginConsts.JVPluginRoot.id, JVConsts.emptyString);
	}

	@Override
	public void matchPluginElement() throws JVException {
		//无需要匹配的对象				
	}

}
