package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;

public class JVPluginExtensionMenus extends JVPluginExtension {

	public JVPluginExtensionMenus(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container, element);
	}

	@Override
	public String getExtensionPoint() {
		return JVPluginConsts.JVPluginMenus.extensionMenus;
	}

}
