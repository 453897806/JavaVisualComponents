package com.JVComponents.Plugin;

import org.dom4j.Element;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVConsts;

/**
 * 命令分组定义节点
 * 
 * @author bob
 *
 */
public class JVPluginElementCategory extends JVPluginElement {

	private JVConfigXMLAttribute id;
	/**
	 * id属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getId() {
		return id;
	}

	private JVConfigXMLAttribute attr_name;
	/**
	 * 得到name属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getAttr_name() {
		return attr_name;
	}

	/**
	 * 针对节点读取属性对象，子类继承读取指定的属性
	 * 
	 * @param element
	 * @throws JVException
	 */
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		id = getXMLAttribute(JVPluginConsts.JVPluginRoot.id, JVConsts.emptyString);
		attr_name = getXMLAttribute(JVPluginConsts.JVPluginRoot.name, JVConsts.emptyString);		
	}

	public JVPluginElementCategory(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}

	@Override
	public void matchPluginElement() throws JVException {
		//无需要匹配的对象		
	}
}
