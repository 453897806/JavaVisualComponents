package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVException;

public class JVPluginExtensionMenus extends JVPluginExtension {

	public JVPluginExtensionMenus(JVPluginXMLFile pluginXMLFile, Element element) throws JVException {
		super(pluginXMLFile, element);
	}

	@Override
	public String getPointValue() {
		return JVPluginConsts.JVPluginMenus.extensionMenus;
	}

}
