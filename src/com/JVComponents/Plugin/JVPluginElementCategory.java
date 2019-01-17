package com.JVComponents.Plugin;

import org.dom4j.Element;

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
	 * 得到id属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getId() throws JVException {
		//缺省id = 组件名称（容器内唯一）
		String id = (String)this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginRoot.id, id);
	}

	/**
	 * 得到name属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getAttr_name() throws JVException {
		//缺省名称 = 组件名称（容器内唯一）
		String name = (String)this.getName().getValue();
		return getAttribute(JVPluginConsts.JVPluginRoot.name, name);
	}

	public JVPluginElementCategory(JVPluginExtension extension, Element element) throws JVException {
		super(extension, element);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.JVPluginCommands.JVPluginCommandCategory.category;
	}
}
