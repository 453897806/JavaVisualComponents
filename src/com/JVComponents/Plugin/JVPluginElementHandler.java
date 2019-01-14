package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementHandler extends JVPluginElement {
	
	/**
	 * commandId属性
	 */
	private JVConfigXMLAttribute commandId;

	public JVConfigXMLAttribute getCommandId() {
		return commandId;
	}

	/**
	 * class属性
	 */
	private JVConfigXMLAttribute attr_class;

	public JVConfigXMLAttribute getAttr_class() {
		return attr_class;
	}

	public JVPluginElementHandler(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.handler;
	}
	
	@Override
	protected void createAttributes() throws JVException {
		super.createAttributes();
		// 2个属性
		this.commandId = getAttribute(JVPluginConsts.commandId);
		this.attr_class = getAttribute(JVPluginConsts.attr_class);
	}

}
