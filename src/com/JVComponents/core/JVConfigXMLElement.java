package com.JVComponents.core;

import org.dom4j.*;
import java.util.*;

public class JVConfigXMLElement extends JVAbstractComponent {

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
	 * 包含该节点的XML文件对象
	 */
	private JVConfigXMLFile configXMLFile;

	public JVConfigXMLFile getConfigXMLFile() {
		return configXMLFile;
	}

	/**
	 * 子节点集合
	 */
	private HashSet<JVConfigXMLElement> subElements;

	public Iterator<JVConfigXMLElement> getElementsIterator() {
		return subElements.iterator();
	}

	/**
	 * 在子节点列表中增加一个子节点
	 * 
	 * @param element
	 * @throws JVException
	 * 
	 */
	public JVConfigXMLElement addSubElement(Element element) throws JVException {
		// 先查找，如果存在则直接返回，否则创建一个
		JVConfigXMLElement result = findSubElement(element);

		if (result == null) {
			// 通过方法创建子节点，子类可以继承并返回不同类型的节点对象
			result = createSubElement(element);
			subElements.add(result);
		}
		return result;
	}

	/**
	 * 创建子节点函数，子类可以继承返回不同类型的节点对象
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLElement createSubElement(Element element) throws JVException {
		// 如果子节点还有子节点则递归创建
		return new JVConfigXMLElement(configXMLFile, element);
	}

	/**
	 * 
	 * 根据传入的节点查找节点对象
	 * 
	 * @param element
	 * @return
	 */
	public JVConfigXMLElement findSubElement(Element element) {
		JVConfigXMLElement result = null;
		Iterator<JVConfigXMLElement> iter = getElementsIterator();
		JVConfigXMLElement tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			if (tmp.getElement() == element) {
				result = tmp;
				break;
			}
		}
		return result;
	}

	/**
	 * 节点属性集合
	 */
	private HashSet<JVConfigXMLAttribute> attributes;
	public Iterator<JVConfigXMLAttribute> getAttributesIterator() {
		return attributes.iterator();
	}
	
	public JVConfigXMLAttribute addAttribute(Attribute attribute) throws JVException {
		// 先查找，如果存在则直接返回，否则创建一个
		JVConfigXMLAttribute result = null;
		
		if (result == null) {
			// 通过方法创建属性，子类可以继承并返回不同类型的属性对象
			result = createAttribute(attribute);
			attributes.add(result);
		}
		
		return result;		
	}
	
	/**
	 * 创建属性函数，子类可以继承返回不同类型的属性对象
	 * 
	 * @param attribute
	 * @return
	 * @throws JVException
	 */
	protected JVConfigXMLAttribute createAttribute(Attribute attribute) throws JVException {
		return new JVConfigXMLAttribute(this, attribute);
	}
	
	/**
	 * 
	 * 根据传入的属性查找属性对象
	 * 
	 * @param attribute
	 * @return
	 */
	public JVConfigXMLAttribute findAttribute(Attribute attribute) {
		JVConfigXMLAttribute result = null;
		Iterator<JVConfigXMLAttribute> iter = getAttributesIterator();
		JVConfigXMLAttribute tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			if (tmp.getAttribute() == attribute) {
				result = tmp;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 根据传入的属性名称查找属性对象
	 * 
	 * @param String
	 * @return
	 */
	public JVConfigXMLAttribute findAttribute(String attribute) {
		JVConfigXMLAttribute result = null;
		Iterator<JVConfigXMLAttribute> iter = getAttributesIterator();
		JVConfigXMLAttribute tmp;
		while (iter.hasNext()) {
			tmp = iter.next();
			if (attribute.equals(tmp.getAttribute().getName())) {
				result = tmp;
				break;
			}
		}
		return result;
	}

	public JVConfigXMLElement(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		//用节点名称
		super(element.getName());

		this.configXMLFile = configXMLFile;
		this.element = element;

		// 子节点集合
		this.subElements = new HashSet<JVConfigXMLElement>();

		// 读取每个子节点并加入到子节点集合中
		Iterator<Element> eiter = element.elementIterator();
		while (eiter.hasNext()) {
			addSubElement(eiter.next());
		}

		// 属性集合
		this.attributes = new HashSet<JVConfigXMLAttribute>();
		// 读取每个属性并加入到属性集合中
		Iterator<Attribute> aiter = element.attributeIterator();
		while (aiter.hasNext()) {
			addAttribute(aiter.next());
		}
	}

}
