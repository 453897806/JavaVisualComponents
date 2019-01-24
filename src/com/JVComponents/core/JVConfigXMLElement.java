package com.JVComponents.core;

import org.dom4j.*;
import java.util.*;

public class JVConfigXMLElement extends JVAbstractComponent {
	
	private Element element;
	
	/**
	 * @return
	 * 
	 * 用于封装的对象
	 * 
	 */
	protected Object getPackagedObject(){
		return element;
	}

	/**
	 * 包含该节点的XML文件对象
	 */
	private JVConfigXMLFile configXMLFile;

	public JVConfigXMLFile getConfigXMLFile() {
		return configXMLFile;
	}

	
	private HashSet<JVConfigXMLElement> subXMLElements;
	
	/**
	 * 子节点集合
	 */
	public HashSet<JVConfigXMLElement> getSubXMLElements() {
		return subXMLElements;
	}
	
	/**
	 * 根据xml节点查找节点对象
	 * 
	 * @param element
	 * @return
	 */
	public JVConfigXMLElement findSubXMLElement(Element element) {
		JVConfigXMLElement result = null;
		Iterator<JVConfigXMLElement> iter = subXMLElements.iterator();
		JVConfigXMLElement tmp;
		while(iter.hasNext()) {
			tmp = iter.next();
			if(element == tmp.getPackagedObject()) {
				result = tmp;
				break;
			}
		}
		
		return result;
	}
	
	private HashSet<JVConfigXMLAttribute> xmlAttributes;

	/**
	 * 节点属性集合
	 * @return
	 */
	public HashSet<JVConfigXMLAttribute> getXmlAttributes() {
		return xmlAttributes;
	}
	
	/**
	 * 根据属性名称和缺省值得到属性对象
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLAttribute getXMLAttribute(String attributeName, String attributeValue) throws JVException{
		//查找属性
		JVConfigXMLAttribute result = findXMLAttribute(attributeName);
		
		//如果不存在则创建
		if(result == null) {
			Attribute attr = this.element.attribute(attributeName);
			if(attr == null) {
				this.element.addAttribute(attributeName, attributeValue);
				attr = this.element.attribute(attributeName);
			}
			result = new JVConfigXMLAttribute(this, attr);
			//加入集合
			xmlAttributes.add(result);
		}
		
		return result;
	}
	
	/**
	 * 根据属性名称查找属性对象
	 * 
	 * @param attributeName
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLAttribute findXMLAttribute(String attributeName) throws JVException{
		JVConfigXMLAttribute result = null;
		Iterator<JVConfigXMLAttribute> iter = xmlAttributes.iterator();
		JVConfigXMLAttribute attr ;
		while (iter.hasNext()) {
			attr = iter.next();
			if(attributeName.equals((String)attr.getName().getValue())){
				result = attr;
				break;
			}
		}
		return result;
	}

	/**
	 * 针对节点读取属性对象，子类继承读取指定的属性
	 * @param element
	 * @throws JVException
	 */
	protected void readAttributes(Element element) throws JVException {
		@SuppressWarnings("unchecked")
		Iterator<Attribute> iter = element.attributeIterator();
		Attribute attr ;
		while(iter.hasNext()){
			attr = iter.next();
			xmlAttributes.add(new JVConfigXMLAttribute(this, attr));
		}
	}
	
	/**
	 * 针对节点读取下级节点对象
	 * 
	 * @param element
	 * @throws JVException
	 */
	protected void readSubElements(Element element) throws JVException{
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = element.elementIterator();
		Element subElement ;
		JVConfigXMLElement xmlElement;
		while(iter.hasNext()) {
			subElement = iter.next();
			//创建子节点对象
			xmlElement = configXMLFile.createXMLElement(subElement);
			subXMLElements.add(xmlElement);
			xmlElement.createByElement(subElement);
		}
	}
	
	/**
	 * 针对节点写入属性对象
	 * @param element
	 * @throws JVException
	 */
	protected void writeAttributes(Element element) throws JVException {
		Iterator<JVConfigXMLAttribute> iter = xmlAttributes.iterator();
		JVConfigXMLAttribute attr ;
		while (iter.hasNext()) {
			attr = iter.next();
			//写入节点对象
			element.addAttribute((String)attr.getName().getValue(), (String)attr.getValue().getValue());
		}
	}
	
	/**
	 * 针对节点写入下级节点对象
	 * 
	 * @param Element
	 * @throws JVException
	 */
	protected void writeSubElements(Element Element) throws JVException{
		Iterator<JVConfigXMLElement> iter = this.subXMLElements.iterator();
		JVConfigXMLElement tmp;
		while(iter.hasNext()) {
			tmp = iter.next();
			//对每一个子节点进行处理
			tmp.writeToDocument(null, Element);
		}
	}
	
	/**
	 * 根据节点内容创建属性和子节点集合
	 * 
	 * @param element
	 * @throws JVException
	 */
	public void createByElement(Element element) throws JVException {
		// 根据属性创建属性对象集合
		readAttributes(element);

		//构建所有子节点
		readSubElements(element);
	}
	
	/**
	 * 根据节点创建
	 * 
	 * @param configXMLFile
	 * @param element
	 * @throws JVException
	 */
	public JVConfigXMLElement(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		// 组件名称
		super(element.getName());
		
		// 成员
		this.configXMLFile = configXMLFile;
		this.subXMLElements = new HashSet<JVConfigXMLElement>();
		this.xmlAttributes = new HashSet<JVConfigXMLAttribute>(); 
		this.element = element;
	}

	public void writeToDocument(Element curElement, Element parentElement) throws JVException{
		String elementName = (String)getName().getValue();

		//构建当前节点
		if(curElement == null) {
			curElement = parentElement.addElement(elementName);
		}
		
		//处理节点属性
		writeAttributes(curElement);

		//处理子节点
		writeSubElements(curElement);
	}
}
