package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementMenuContribution extends JVPluginElement {
	
	public JVConfigXMLAttribute getLocationURI() throws JVException{
		//locationURI,缺省为主菜单
		return getAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.locationURI,
				JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.locationURI_MainMemu);
	}

	public JVPluginElementMenuContribution(JVPluginExtension extension, Element element) throws JVException {
		super(extension, element);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.menuContribution;
	}

}
