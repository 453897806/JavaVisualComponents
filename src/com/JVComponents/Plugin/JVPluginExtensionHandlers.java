package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVException;

public class JVPluginExtensionHandlers extends JVPluginExtension {

	public JVPluginExtensionHandlers(JVPluginXMLFile pluginXMLFile, Element element) throws JVException {
		super(pluginXMLFile, element);
	}

	@Override
	public String getPointValue() {
		return JVPluginConsts.JVPluginHandlers.extensionHandlers;
	}

}
