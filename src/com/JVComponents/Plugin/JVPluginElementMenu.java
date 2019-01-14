package com.JVComponents.Plugin;

import com.JVComponents.core.JVException;

public class JVPluginElementMenu extends JVPluginElement {

	public JVPluginElementMenu(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.menu;
	}

	
}
