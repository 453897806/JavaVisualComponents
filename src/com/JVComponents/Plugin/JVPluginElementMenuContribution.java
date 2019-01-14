package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementMenuContribution extends JVPluginElement {
	
	private JVConfigXMLAttribute locationURI;
	public JVConfigXMLAttribute getLocationURI() {
		return locationURI;
	}

	public JVPluginElementMenuContribution(JVPluginExtension extension) throws JVException {
		super(extension);
		
		this.locationURI = getAttribute(JVPluginConsts.locationURI);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.menuContribution;
	}

}
