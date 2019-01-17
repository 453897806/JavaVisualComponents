package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementKey extends JVPluginElement {
	
	/**
	 * command对象
	 */
	private JVPluginElementCommand command;

	public JVPluginElementCommand getCommand() {
		return command;
	}

	public void setCommand(JVPluginElementCommand command) {
		this.command = command;
	}

	public JVConfigXMLAttribute getSchemeId() throws JVException {
		String str = JVPluginConsts.JVPluginBindings.JVPluginKey.schemeId_value;
		return getAttribute(JVPluginConsts.JVPluginBindings.JVPluginKey.schemeId, str);
	}

	public JVConfigXMLAttribute getContextId() throws JVException {
		String str = JVPluginConsts.JVPluginBindings.JVPluginKey.contextId_value;
		return getAttribute(JVPluginConsts.JVPluginBindings.JVPluginKey.contextId, str);
	}

	public JVConfigXMLAttribute getSequence() throws JVException {
		String str = JVPluginConsts.JVPluginBindings.JVPluginKey.sequence_value;
		return getAttribute(JVPluginConsts.JVPluginBindings.JVPluginKey.sequence, str);
	}

	public JVPluginElementKey(JVPluginExtension extension, Element element) throws JVException {
		super(extension, element);
	}
	
	/**
	 * 父类读取commandId属性后，需要根据该值找到command对象
	 * @throws JVException 
	 */
	@Override
	public void createPluginElment() throws JVException {
		super.createPluginElment();
		
		//根据commandId值找到command对象
		String str = JVPluginConsts.JVPluginCommands.JVPluginCommand.commandId;
		JVComponent cmp = findComponentByName(str);
		if((cmp != null) & (cmp instanceof JVPluginElementCommand)) {
			this.command = (JVPluginElementCommand) cmp;
		}
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginBindings.JVPluginKey.key;
	}

}
