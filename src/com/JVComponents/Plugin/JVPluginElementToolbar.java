package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementToolbar extends JVPluginElement {

	/**
	 * id属性
	 */
	private JVConfigXMLAttribute id;

	public JVConfigXMLAttribute getId() {
		return id;
	}
	
	public JVPluginElementToolbar(JVPluginExtension extension) throws JVException {
		super(extension);
		
		this.id = getAttribute(JVPluginConsts.id);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.toolbar;
	}

}
