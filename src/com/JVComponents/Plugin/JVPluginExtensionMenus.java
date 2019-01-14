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
		return JVPluginConsts.extensionMenus;
	}

	@Override
	public void createPluginExtension() throws JVException {
		// TODO Auto-generated method stub
		super.createPluginExtension();
	}

	@Override
	public void readPluginExtension() throws JVException {
		// TODO Auto-generated method stub
		super.readPluginExtension();
	}

}
