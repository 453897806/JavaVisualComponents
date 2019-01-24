package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConsts;
import com.JVComponents.core.JVException;

public class JVPluginElementMenuCommand extends JVPluginElement {
	
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
	
	private JVConfigXMLAttribute mnemonic;
	/**
	 * 得到mnemonic属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getMnemonic() {
		return mnemonic;
	}
	
	private JVConfigXMLAttribute icon;
	/**
	 * 得到icon属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getIcon() throws JVException{
		//图片文件位置
		return icon;
	}
	
	private JVConfigXMLAttribute tooltip;
	/**
	 * 得到tooltip属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getTooltip() {
		return tooltip;
	}

	public JVPluginElementMenuCommand(JVPluginXMLFile pluginXMLFile, JVPluginExtension extension, Element element) throws JVException {
		super(pluginXMLFile, extension, element);
	}
	
	@Override
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		commandId = getXMLAttribute(JVPluginConsts.JVPluginCommands.JVPluginCommand.commandId, JVConsts.emptyString);
		mnemonic = getXMLAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic, JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic_value);
		icon = getXMLAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.icon, JVConsts.emptyString);
		tooltip = getXMLAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.tooltip, JVConsts.emptyString);
	}
	
	@Override
	public void matchPluginElement() throws JVException {
		JVPluginExtensionCommands extension = (JVPluginExtensionCommands)getExtension().getPluginFile().findExtension(JVPluginExtensionCommands.class);
		//读取commandId属性后，需要根据该值找到command对象
		this.command = extension.findCommand((String)this.commandId.getValue().getValue());		
	}

}
