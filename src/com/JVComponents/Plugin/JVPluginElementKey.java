package com.JVComponents.Plugin;

import com.JVComponents.core.JVException;

public class JVPluginElementKey extends JVPluginElement {

	public JVPluginElementKey(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.key;
	}

}
