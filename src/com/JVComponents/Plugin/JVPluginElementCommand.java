package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVComponent;

public class JVPluginElementCommand extends JVPluginElement {
	/**
	 * id属性
	 * 
	 * @throws JVException
	 */
	public JVConfigXMLAttribute getId() throws JVException {
		// 缺省id = 组件名称（容器内唯一）
		String id = (String) this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginRoot.id, id);
	}

	/**
	 * name属性
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getAttr_name() throws JVException {
		//缺省名称 = 组件名称（容器内唯一）
		String name = (String)this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginRoot.name, name);
	}

	public JVPluginElementCommand(JVPluginExtension extension, Element element) throws JVException {
		super(extension, element);
	}

	private JVPluginElementCategory category;
	
	/**
	 * category对象
	 */ 
	public JVPluginElementCategory getCategory() {
		return this.category;
	}

	public void setCategory(JVPluginElementCategory category) {
		this.category = category;
	}
	
	/**
	 * 父类读取categoryId属性后，需要根据该值找到category对象
	 * @throws JVException 
	 */
	@Override
	public void createPluginElment() throws JVException {
		super.createPluginElment();
		
		String str = JVPluginConsts.JVPluginCommands.JVPluginCommandCategory.categoryId;
		JVComponent cmp = findComponentByName(str);
		if((cmp != null) & (cmp instanceof JVPluginElementCategory)) {
			this.category = (JVPluginElementCategory) cmp;
		}
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginCommands.JVPluginCommand.command;
	}
}
