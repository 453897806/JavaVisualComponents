package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConsts;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVirtualComponent;

/**
 * 
 * 封装了plugin下的扩展
 * 
 * @author DELL
 *
 */
public abstract class JVPluginExtension extends JVirtualComponent {

	/**
	 * 对应的plugin文件
	 */
	private JVPluginXMLFile pluginFile;

	public JVPluginXMLFile getPluginFile() {
		return pluginFile;
	}

	/**
	 * 对应的节点组件
	 */
	private JVConfigXMLElement element;

	public JVConfigXMLElement getElement() {
		return element;
	}

	/**
	 * 
	 * 子类继承并返回扩展点名称
	 * 
	 * @return
	 */
	public abstract String getExtensionPoint();

	/**
	 * point属性
	 */
	private JVConfigXMLAttribute point;

	public JVConfigXMLAttribute getPoint() {
		return point;
	}

	/**
	 * 
	 * 创建扩展对象的机构体，子类需要继承以创建不同类型的结构体
	 * 
	 * @throws JVException
	 * 
	 */
	public void createPluginExtension() throws JVException {
		// point属性
		this.point = new JVConfigXMLAttribute(element, JVPluginConsts.attributePoint);
	}

	/**
	 * 
	 * 根据节点读取扩展对象的内容并检查，子类需要继承以读取不同类型的数据
	 * 
	 */
	public void readPluginExtension() throws JVException {

		// point属性对象内容与当前扩展点一致
		String pointValue = (String) point.getValue().getValue();
		// 如果point属性为空表示新建
		if (pointValue.equals(JVConsts.emptyString)) {
			point.getValue().setValue(getExtensionPoint());
		} else {
			// 不为空就要检查是否一直
			if (!pointValue.equals(getExtensionPoint())) {
				throw new JVException("扩展点不是<" + getExtensionPoint() + ">。", null);
			}
		}

	}

	/**
	 * 根据已经存在的节点进行创建
	 * 
	 * @param container
	 * @param element
	 * @throws JVException
	 */
	public JVPluginExtension(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container);

		// 检查
		if (!(container instanceof JVPluginXMLFile)) {
			throw new JVException("不是Plugin的XML文件！", null);
		}

		// 成员
		this.pluginFile = (JVPluginXMLFile) container;
		this.element = element;

		if (pluginFile != element.getConfigXMLFile()) {
			throw new JVException("节点与plugin文件不是同一个对象！", null);
		}

		// 创建扩展对象的机构体
		createPluginExtension();

		// 根据节点读取扩展对象内容
		readPluginExtension();
	}

}
