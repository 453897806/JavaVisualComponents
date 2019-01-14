package com.JVComponents.Plugin;

import com.JVComponents.core.JVException;

public class JVPluginElementMenuContribution extends JVPluginElement {

	public JVPluginElementMenuContribution(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.menuContribution;
	}

}
