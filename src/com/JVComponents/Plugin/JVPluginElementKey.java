package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementKey extends JVPluginElement {
	
	/**
	 * commandId属性
	 */
	private JVConfigXMLAttribute commandId;

	public JVConfigXMLAttribute getCommandId() {
		return commandId;
	}

	private JVConfigXMLAttribute schemeId;
	
	public JVConfigXMLAttribute getSchemeId() {
		return schemeId;
	}

	private JVConfigXMLAttribute contextId;
	public JVConfigXMLAttribute getContextId() {
		return contextId;
	}

	private JVConfigXMLAttribute sequence;
	public JVConfigXMLAttribute getSequence() {
		return sequence;
	}

	public JVPluginElementKey(JVPluginExtension extension) throws JVException {
		super(extension);
		
		// 成员
		this.commandId = getAttribute(JVPluginConsts.commandId);
		this.schemeId = getAttribute(JVPluginConsts.schemeId);
		this.contextId = getAttribute(JVPluginConsts.contextId);
		this.sequence = getAttribute(JVPluginConsts.sequence);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.key;
	}

}
