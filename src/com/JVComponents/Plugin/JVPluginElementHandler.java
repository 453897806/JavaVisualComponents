package com.JVComponents.Plugin;

import com.JVComponents.core.JVException;

public class JVPluginElementHandler extends JVPluginElement {

	public JVPluginElementHandler(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.handler;
	}

}
