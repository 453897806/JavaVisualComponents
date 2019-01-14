package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementCommand extends JVPluginElement {
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

	public JVConfigXMLAttribute getAttr_name() {
		return attr_name;
	}

	public JVPluginElementCommand(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	protected void createAttributes() throws JVException {
		super.createAttributes();
		// 2个属性id和name
		this.id = getAttribute(JVPluginConsts.id);
		this.attr_name = getAttribute(JVPluginConsts.name);
		// 第三个属性categoryId
		this.categoryId = getAttribute(JVPluginConsts.categoryId);
	}

	/**
	 * CategoryId属性
	 */ 
	private JVConfigXMLAttribute categoryId;

	public JVConfigXMLAttribute getCategoryId() {
		return categoryId;
	}

	/**
	 * category对象
	 */ 
	private JVPluginElementCategory category;

	public JVPluginElementCategory getCategory() {
		return category;
	}

	public void setCategory(JVPluginElementCategory category) throws JVException {
		if (this.category != category) {
			this.category = category;
			// 改变categoryId属性
			String str = (String) category.getId().getValue().getValue();
			categoryId.getValue().setValue(str);
		}
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.command;
	}
}
