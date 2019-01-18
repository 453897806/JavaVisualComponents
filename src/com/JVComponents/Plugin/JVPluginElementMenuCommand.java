package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVComponent;
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
	
	/**
	 * 得到id属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getId() throws JVException {
		//缺省id = 组件名称（容器内唯一）
		String id = (String)this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginRoot.id, id);
	}
	
	public JVConfigXMLAttribute getMnemonic() throws JVException {
		String str = JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic_value;
		return getAttribute(JVPluginConsts.JVPluginMenus.JVPluginMenu.mnemonic, str);
	}

	public JVConfigXMLAttribute getIcon() throws JVException{
		//图片文件位置
		return getAttribute(JVPluginConsts.JVPluginRoot.id, "");
	}

	public JVPluginElementMenuCommand(JVPluginExtension extension, Element element) throws JVException {
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
		return JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand;
	}

}
