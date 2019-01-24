package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

public class JVPluginExtensionHandlers extends JVPluginExtension {

	public JVPluginExtensionHandlers(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}

	@Override
	public String getPointValue() {
		return JVPluginConsts.JVPluginHandlers.extensionHandlers;
	}

}
