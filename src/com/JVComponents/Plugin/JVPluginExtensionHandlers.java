package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;

public class JVPluginExtensionHandlers extends JVPluginExtension {

	public JVPluginExtensionHandlers(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container, element);
	}

	@Override
	public String getExtensionPoint() {
		return JVPluginConsts.JVPluginHandlers.extensionHandlers;
	}

}
