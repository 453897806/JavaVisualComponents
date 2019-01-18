package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVComponent;
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

	/**
	 * class属性
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getAttr_class() throws JVException {
		String str = JVConsts.nullString;
		return getAttribute(JVPluginConsts.JVPluginRoot.attr_class, str);
	}

	public JVPluginElementHandler(JVPluginExtension extension, Element element) throws JVException {
		//用缺省组件名命名
		super(extension, element, element.attributeValue(JVConsts.componentDefualtName));
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
		return JVPluginConsts.JVPluginHandlers.JVPluginHandler.handler;
	}

}
