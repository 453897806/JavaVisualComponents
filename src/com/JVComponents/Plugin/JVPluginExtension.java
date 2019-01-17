package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;

import java.util.HashSet;
import java.util.Iterator;

import org.dom4j.Element;

import com.JVComponents.core.JVComponent;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVirtualComponent;

/**
 * 
 * 以虚拟组件形式封装了plugin下的扩展Extension
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
	 * 得到point属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getPoint() throws JVException {
		return this.element.getAttribute(getExtensionPoint(), getExtensionPoint());
	}

	/**
	 * 
	 * 创建扩展对象的机构体，子类可继承以创建不同类型的结构体
	 * 
	 * @throws JVException
	 * 
	 */
	protected void createPluginExtension() throws JVException {
		// point属性对象内容与当前扩展点一致
		String pointValue = (String) getPoint().getValue().getValue();
		//检查一致性
		if(! pointValue.equals(getExtensionPoint())) {
			throw new JVException("扩展点不是<" + getExtensionPoint() + ">。", null);
		}

		//读取子节点并创建结构对象
		Iterator<Element> iter = element.getElement().elementIterator();
		Element element;
		while(iter.hasNext()) {
			element = iter.next();
			//根据节点创建节点对象
			createPluginElement(element);
		}	
	}
	
	/**
	 * 根据组件名称查找
	 * @param componentName
	 * @return
	 * @throws JVException
	 */
	public JVComponent findComponent(String componentName) throws JVException {
		return  getContainer().findComponent(componentName);
	}

	private HashSet<JVPluginElement> pluginElements;
	
	/**
	 * 根据节点名称返回对应子节点集合
	 * 
	 * @param elementName
	 * 		节点的名称
	 * @return
	 * @throws JVException
	 */
	public Iterator<JVPluginElement> getPluginElement(String elementName) throws JVException{
		HashSet<JVPluginElement> result = new HashSet<JVPluginElement>();
		
		Iterator<JVPluginElement> iter = pluginElements.iterator();
		JVPluginElement tmp;
		String str;
		while(iter.hasNext()) {
			tmp = iter.next();
			str = (String)tmp.getName().getValue();
			if(elementName.equals(str)) {
				result.add(tmp);
			}
		}
		
		return result.iterator();
	}
	
	/**
	 * 
	 * 根据xml节点增加一个plugin节点对象
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public JVPluginElement createPluginElement(Element element) throws JVException {
		//通过工厂创建，创建过程中读取内容
		JVPluginElement result = JVPluginExtensionFactory.createPluginElement(this, element);
		//加入集合
		pluginElements.add(result);
		//返回
		return result;
	}
	
	/**
	 * 
	 * 根据plugin节点类型创建一个节点对象
	 * 
	 * @param elementName
	 * @return
	 * @throws JVException
	 */
	public JVPluginElement createPluginElement(String elementName) throws JVException {
		//根据名称创建节点
		Element element = this.element.getElement().addElement(elementName);
		//返回
		return createPluginElement(element);
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
	}

}
