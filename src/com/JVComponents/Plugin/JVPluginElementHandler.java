package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVConsts;

public class JVPluginElementHandler extends JVPluginElement {
	
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

	private JVConfigXMLAttribute attr_class;
	/**
	 * class属性
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getAttr_class() {
		return attr_class;
	}

	public JVPluginElementHandler(JVPluginXMLFile pluginXMLFile, JVPluginExtension extension, Element element) throws JVException {
		super(pluginXMLFile, extension, element);
	}
	
	@Override
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		attr_class = getXMLAttribute(JVPluginConsts.JVPluginRoot.attr_class, JVConsts.emptyString);
		commandId = getXMLAttribute(JVPluginConsts.JVPluginCommands.JVPluginCommand.commandId, JVConsts.emptyString);
	}

	@Override
	public void matchPluginElement() throws JVException {
		JVPluginExtensionCommands extension = (JVPluginExtensionCommands)getExtension().getPluginFile().findExtension(JVPluginExtensionCommands.class);
		//读取commandId属性后，需要根据该值找到command对象
		this.command = extension.findCommand((String)this.commandId.getValue().getValue());
	}

}
