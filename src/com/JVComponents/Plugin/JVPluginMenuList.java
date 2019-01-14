package com.JVComponents.Plugin;

import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVMenuList;

public class JVPluginMenuList extends JVMenuList {
	
	public JVPluginXMLFile getPluginXMLFile() {
		return (JVPluginXMLFile)getContainer();
	}

	public JVPluginMenuList(JVContainer container) throws JVException {
		super(container);
	}

}
