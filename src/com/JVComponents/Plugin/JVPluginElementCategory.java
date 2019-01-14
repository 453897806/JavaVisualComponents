package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

/**
 * 命令分组定义节点
 * 
 * @author bob
 *
 */
public class JVPluginElementCategory extends JVPluginElement {

	/**
	 * id属性
	 */
	private JVConfigXMLAttribute id;

	public JVConfigXMLAttribute getId() {
		return id;
	}

	/**
	 * name属性
	 */
	private JVConfigXMLAttribute attr_name;

	public JVConfigXMLAttribute getAttr_ame() {
		return attr_name;
	}

	public JVPluginElementCategory(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	protected void createAttributes() throws JVException {
		super.createAttributes();
		// 2个属性id和name
		this.id = getAttribute(JVPluginConsts.id);
		this.attr_name = getAttribute(JVPluginConsts.name);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.category;
	}

}
