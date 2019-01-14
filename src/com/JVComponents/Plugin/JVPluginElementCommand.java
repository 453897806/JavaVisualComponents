package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

public class JVPluginElementCommand extends JVPluginElement {

	public JVPluginElementCommand(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	protected void createAttributes() throws JVException {
		super.createAttributes();

		// 第三个属性categoryId
		this.attributeCategoryId = getAttribute(JVPluginConsts.categoryId);
	}
	
	//CategoryId属性
	private JVConfigXMLAttribute attributeCategoryId;
	public JVConfigXMLAttribute getAttributeCategoryId() {
		return attributeCategoryId;
	} 
	
	//category对象
	private JVPluginElementCategory category;
	public JVPluginElementCategory getCategory() {
		return category;
	}

	public void setCategory(JVPluginElementCategory category) throws JVException {
		if(this.category != category) {
			this.category = category;
			//改变categoryId属性
			String str = (String) category.getAttributeId().getValue().getValue();
			attributeCategoryId.getValue().setValue(str);
		}		
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.command;
	}
}
