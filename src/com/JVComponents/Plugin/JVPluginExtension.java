package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
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
	 * 根据已经存在的节点进行创建
	 * 
	 * @param container
	 * @param element
	 * @throws JVException
	 */
	public JVPluginExtension(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container);
		
		//检查
		if(!(container instanceof JVPluginXMLFile)){
			throw new  JVException("不是Plugin的XML文件！",null);
		}
		//成员
		this.pluginFile = (JVPluginXMLFile) container;
		this.element = element;
		
		if(pluginFile != element.getConfigXMLFile()) {
			throw new  JVException("节点与plugin文件不是同一个对象！",null);
		}
		
		//point属性对象
		point = element.findAttribute(JVPluginConsts.attributePoint);
		if(point == null) {
			throw new JVException("无<" + JVPluginConsts.attributePoint +">属性。", null);
		}
		
		//point属性对象内容与当前扩展点一致
		String pointValue = (String)point.getValue().getValue();
		if(!pointValue.equals(getExtensionPoint())) {
			throw new JVException("扩展点不是<" + getExtensionPoint() +">。", null);
		}
		
		//根据节点构建属性和下级节点
		createSubNode();
	}
	
}
