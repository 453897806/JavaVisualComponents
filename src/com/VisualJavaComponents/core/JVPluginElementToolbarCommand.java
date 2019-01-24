package com.VisualJavaComponents.core;

import org.dom4j.Element;

import com.JVComponents.Plugin.JVPluginConsts;
import com.JVComponents.Plugin.JVPluginElement;
import com.JVComponents.Plugin.JVPluginElementCommand;
import com.JVComponents.Plugin.JVPluginExtension;
import com.JVComponents.Plugin.JVPluginConsts.JVPluginMenus;
import com.JVComponents.Plugin.JVPluginConsts.JVPluginRoot;
import com.JVComponents.Plugin.JVPluginConsts.JVPluginMenus.JVPluginToolbar;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConsts;
import com.JVComponents.core.JVException;

public class JVPluginElementToolbarCommand extends JVPluginElement {

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


	public JVConfigXMLAttribute getTooltip() throws JVException{
		//hint
		return getAttribute(JVPluginConsts.JVPluginMenus.JVPluginToolbar.tooltip, "");
	}

	public JVConfigXMLAttribute getIcon() throws JVException{
		//图片文件位置
		return getAttribute(JVPluginConsts.JVPluginRoot.id, "");
	}

	public JVPluginElementToolbarCommand(JVPluginExtension extension, Element element) throws JVException {
		super(extension, element);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginMenus.JVPluginToolbar.toolbarCommand;
	}

}
