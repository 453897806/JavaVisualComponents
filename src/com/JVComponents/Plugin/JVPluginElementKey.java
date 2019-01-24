package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConsts;
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
	
	private JVConfigXMLAttribute commandId;
	/**
	 * 得到commandId属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getCommandId() {
		return commandId;
	}
	
	private JVConfigXMLAttribute schemeId;
	private JVConfigXMLAttribute contextId;
	private JVConfigXMLAttribute sequence;
	
	
	public JVConfigXMLAttribute getSchemeId() {
		return schemeId;
	}

	public JVConfigXMLAttribute getContextId() {
		return contextId;
	}

	public JVConfigXMLAttribute getSequence() {
		return sequence;
	}

	public JVPluginElementKey(JVPluginXMLFile pluginXMLFile, JVPluginExtension extension, Element element) throws JVException {
		super(pluginXMLFile, extension, element);
	}
	
	@Override
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		commandId = getXMLAttribute(JVPluginConsts.JVPluginCommands.JVPluginCommand.commandId, JVConsts.emptyString);
		schemeId = getXMLAttribute(JVPluginConsts.JVPluginBindings.JVPluginKey.schemeId, JVPluginConsts.JVPluginBindings.JVPluginKey.schemeId_value);
		contextId = getXMLAttribute(JVPluginConsts.JVPluginBindings.JVPluginKey.contextId, JVPluginConsts.JVPluginBindings.JVPluginKey.contextId_value);
		sequence = getXMLAttribute(JVPluginConsts.JVPluginBindings.JVPluginKey.sequence, JVPluginConsts.JVPluginBindings.JVPluginKey.sequence_value);
	}

	@Override
	public void matchPluginElement() throws JVException {
		JVPluginExtensionCommands extension = (JVPluginExtensionCommands)getExtension().getPluginFile().findExtension(JVPluginExtensionCommands.class);
		//读取commandId属性后，需要根据该值找到command对象
		this.command = extension.findCommand((String)this.commandId.getValue().getValue());
		
	}

}
