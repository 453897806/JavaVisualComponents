package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementMenu extends JVPluginElement {

	/**
	 * id属性
	 */
	private JVConfigXMLAttribute id;
	public JVConfigXMLAttribute getId() {
		return id;
	}

	private JVConfigXMLAttribute label;
	public JVConfigXMLAttribute getLabel() {
		return label;
	}

	private JVConfigXMLAttribute mnemonic;
	public JVConfigXMLAttribute getMnemonic() {
		return mnemonic;
	}

	public JVPluginElementMenu(JVPluginExtension extension) throws JVException {
		super(extension);
		
		this.id = getAttribute(JVPluginConsts.id);
		this.label = getAttribute(JVPluginConsts.label);
		this.mnemonic = getAttribute(JVPluginConsts.mnemonic);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.menu;
	}

	
}
