package com.VisualJavaComponents.core;

import com.JVComponents.Plugin.JVPluginXMLFile;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVMenus;

public class JVPluginMenus extends JVMenus {
	
	public JVPluginXMLFile getPluginXMLFile() {
		return (JVPluginXMLFile)getContainer();
	}

	public JVPluginMenus(JVContainer container) throws JVException {
		super(container);
	}

}
