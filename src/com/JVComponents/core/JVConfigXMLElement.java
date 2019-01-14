package com.JVComponents.core;

import org.dom4j.*;
import java.util.*;

public class JVConfigXMLElement extends JVAbstractComponent {

	/**
	 * 包含该节点的XML文件对象
	 */
	private JVConfigXMLFile configXMLFile;

	public JVConfigXMLFile getConfigXMLFile() {
		return configXMLFile;
	}

	/**
	 * 对应的节点
	 */
	private Element element;

	public Element getElement() {
		return element;
	}

	/**
	 * @return
	 * 
	 * 		用于封装的对象
	 * 
	 */
	protected Object getPackagedObject() {
		return element;
	}

	/**
	 * 属性集合
	 */
	private HashSet<JVConfigXMLAttribute> attributes;

	/**
	 * 
	 * 根据属性名称得到一个属性对象，如果不存在则创建
	 * 
	 * @param attributeName
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLAttribute getAttribute(String attributeName) throws JVException {
		// 查找一个属性
		JVConfigXMLAttribute result = findAttribute(attributeName);
		// 如果没有找到则创建一个
		if (result == null) {
			result = new JVConfigXMLAttribute(this, attributeName, JVConsts.nullString);
			attributes.add(result);
		}
		return result;
	}

	public JVConfigXMLAttribute getAttribute(Attribute attribute) throws JVException {
		// 查找一个属性
		JVConfigXMLAttribute result = findAttribute(attribute);
		// 如果没有找到则创建一个
		if (result == null) {
			result = new JVConfigXMLAttribute(this, attribute);
			attributes.add(result);
		}
		return result;
	}

	/**
	 * 
	 * 根据属性名称查找一个属性对象
	 * 
	 * @param attributeName
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLAttribute findAttribute(String attributeName) throws JVException {
		JVConfigXMLAttribute result = null;

		Iterator<JVConfigXMLAttribute> iter = attributes.iterator();
		JVConfigXMLAttribute tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			if (attributeName.equals((String) tmp.getName().getValue())) {
				result = tmp;
				break;
			}
		}
		return result;
	}

	public JVConfigXMLAttribute findAttribute(Attribute attribute) {
		JVConfigXMLAttribute result = null;

		Iterator<JVConfigXMLAttribute> iter = attributes.iterator();
		JVConfigXMLAttribute tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			if (attribute == tmp.getAttribute()) {
				result = tmp;
				break;
			}
		}
		return result;
	}

	/**
	 * 针对节点创建所有属性对象
	 * 
	 * @throws JVException
	 */
	protected void createAttributes() throws JVException {
		// 根据属性创建属性对象集合
		this.attributes = new HashSet<JVConfigXMLAttribute>();
		Iterator<Attribute> iter = element.attributeIterator();
		Attribute tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			getAttribute(tmp);
		}
	}

	/**
	 * 根据节点创建
	 * 
	 * @param configXMLFile
	 * @param element
	 * @throws JVException
	 */
	public JVConfigXMLElement(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		// 用节点名称
		super(element.getName());

		// 成员
		this.configXMLFile = configXMLFile;
		this.element = element;

		// 根据属性创建属性对象集合
		createAttributes();
	}

	/**
	 * 根据节点名称，在父节点下创建
	 * 
	 * @param configXMLFile
	 * @param parentElement
	 * 	父节点
	 * 
	 * @param elementName
	 * 需要创建的节点名称，如果没有则创建
	 * 
	 * @throws JVException
	 */
	public JVConfigXMLElement(JVConfigXMLFile configXMLFile, Element parentElement, String elementName) throws JVException {
		// 用节点名称
		super(elementName);

		// 成员
		this.configXMLFile = configXMLFile;
		this.element = parentElement.element(elementName);

		if (this.element == null) {
			this.element = parentElement.addElement(elementName);
		}

		// 根据属性创建属性对象集合
		createAttributes();
	}

}
