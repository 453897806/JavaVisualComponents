package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConsts;

import java.util.HashSet;
import java.util.Iterator;

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
	 * 
	 * @throws JVException
	 */
	public JVPluginXMLFile getPluginFile() throws JVException {
		// 检查
		if (!(getContainer() instanceof JVPluginXMLFile)) {
			throw new JVException("不是Plugin的XML文件！", null);
		}

		return (JVPluginXMLFile) getContainer();
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
	protected void createPluginExtension() throws JVException {
		// point属性，其值为子类继承后指定的值
		String pointValue = getExtensionPoint();
		this.point = new JVConfigXMLAttribute(element, JVPluginConsts.attributePoint, pointValue);
		
		//子类继承创建其他子节点
	}

	/**
	 * 
	 * 根据节点读取扩展对象的内容并检查，子类需要继承以读取不同类型的数据
	 * 
	 */
	public void readPluginExtension() throws JVException {

		// point属性对象内容与当前扩展点一致
		String pointValue = (String) point.getValue().getValue();
		//检查一致性
		if(! pointValue.equals(getExtensionPoint())) {
			throw new JVException("扩展点不是<" + getExtensionPoint() + ">。", null);
		}
		
		//子类继承读取其他节点

	}
	
	private HashSet<JVPluginElement> pluginElements;
	
	public Iterator<JVPluginElement> getPluginElement(String elementType) throws JVException{
		HashSet<JVPluginElement> result = new HashSet<JVPluginElement>();
		
		Iterator<JVPluginElement> iter = pluginElements.iterator();
		JVPluginElement tmp;
		String str;
		while(iter.hasNext()) {
			tmp = iter.next();
			str = (String)tmp.getName().getValue();
			if(elementType.equals(str)) {
				result.add(tmp);
			}
		}
		
		return result.iterator();
	}
	
	public JVPluginElement addPluginElement(String elementType) throws JVException {
		//通过工厂创建
		JVPluginElement result = JVPluginExtensionFactory.createPluginElement(this, elementType);
		//加入集合
		pluginElements.add(result);
		//返回
		return result;
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

		pluginElements = new HashSet<JVPluginElement>();
		
		//检查一致性
		if (getPluginFile() != element.getConfigXMLFile()) {
			throw new JVException("节点与plugin文件不是同一个对象！", null);
		}
		// 成员
		this.element = element;


		// 创建扩展对象的结构体
		createPluginExtension();

		// 根据节点读取扩展对象内容
		readPluginExtension();
	}

}
