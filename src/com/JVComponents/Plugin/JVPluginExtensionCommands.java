package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;

/**
 * Commands¿©’π
 * 
 * @author DELL
 *
 */
public class JVPluginExtensionCommands extends JVPluginExtension {

	public JVPluginExtensionCommands(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container, element);
		
	}
	
	@Override
	public String getExtensionPoint() {
		return JVPluginConsts.extensionCommands;
	}

}
