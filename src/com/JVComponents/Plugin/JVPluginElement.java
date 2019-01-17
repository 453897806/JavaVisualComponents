package com.JVComponents.Plugin;

import org.dom4j.Element;

import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVException;

/**
 * 
 * 所有plugin中节点的基类
 * 
 * @author bob
 *
 */
public abstract class JVPluginElement extends JVConfigXMLElement {

	private JVPluginExtension extension;

	public JVPluginExtension getExtension() {
		return extension;
	}

	public JVPluginElement(JVPluginExtension extension, Element element) throws JVException {
		// 父类创建过程中自动读取了属性
		super(extension.getPluginFile(), element);

		// 成员
		this.extension = extension;

		// 特殊处理需要子类完成
		createPluginElment();
	}

	/**
	 * 当父类读取并创建了属性后，需要处理属性和其他子节点，子类继承后完成具体内容
	 * 
	 * @throws JVException
	 */
	public void createPluginElment() throws JVException {

	}

	/**
	 * 根据节点属性值查找对象
	 * 
	 * @param attributeName
	 * @return
	 * @throws JVException
	 */
	public JVComponent findComponentByName(String attributeName) throws JVException {
		JVComponent result = null;
		// 根据属性名称值找到对应的对象
		JVConfigXMLAttribute attr = findAttribute(attributeName);
		if (attr != null) {
			//属性值
			String str = (String) attr.getValue().getValue();
			JVComponent cmp = getExtension().findComponent(str);
			if (cmp != null) {
				result = cmp;
			}
		}
		return result;
	}

	/**
	 * 
	 * 返回节点类型，子类需要继承
	 * 
	 * @return
	 */
	public abstract String getElementType();

}
