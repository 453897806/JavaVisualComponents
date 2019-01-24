package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVException;

public class JVPluginExtensionBindings extends JVPluginExtension {

	public JVPluginExtensionBindings(JVPluginXMLFile pluginXMLFile, Element element) throws JVException {
		super(pluginXMLFile, element);
	}

	@Override
	public String getPointValue() {
		return JVPluginConsts.JVPluginBindings.extensionBindings;
	}

}
