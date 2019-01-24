package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

public class JVPluginExtensionMenus extends JVPluginExtension {

	public JVPluginExtensionMenus(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}

	@Override
	public String getPointValue() {
		return JVPluginConsts.JVPluginMenus.extensionMenus;
	}

}
