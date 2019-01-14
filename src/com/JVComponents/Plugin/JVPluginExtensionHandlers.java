package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;

public class JVPluginExtensionHandlers extends JVPluginExtension {

	@Override
	public void createPluginExtension() throws JVException {
		super.createPluginExtension();
	}

	@Override
	public void readPluginExtension() throws JVException {
		super.readPluginExtension();
	}

	public JVPluginExtensionHandlers(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container, element);
	}

	@Override
	public String getExtensionPoint() {
		return JVPluginConsts.extensionHandlers;
	}

}
