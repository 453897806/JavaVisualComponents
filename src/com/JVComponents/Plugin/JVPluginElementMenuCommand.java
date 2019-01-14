package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementMenuCommand extends JVPluginElement {
	
	/**
	 * commandId属性
	 */
	private JVConfigXMLAttribute commandId;

	public JVConfigXMLAttribute getCommandId() {
		return commandId;
	}
	
	/**
	 * id属性
	 */
	private JVConfigXMLAttribute id;

	public JVConfigXMLAttribute getId() {
		return id;
	}
	
	private JVConfigXMLAttribute mnemonic;
	
	public JVConfigXMLAttribute getMnemonic() {
		return mnemonic;
	}

	private JVConfigXMLAttribute icon;
	public JVConfigXMLAttribute getIcon() {
		return icon;
	}

	public JVPluginElementMenuCommand(JVPluginExtension extension) throws JVException {
		super(extension);
		// 成员
		commandId = getAttribute(JVPluginConsts.commandId);
		id = getAttribute(JVPluginConsts.id);
		mnemonic = getAttribute(JVPluginConsts.mnemonic);
		icon = getAttribute(JVPluginConsts.icon);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.menuCommand;
	}

}
