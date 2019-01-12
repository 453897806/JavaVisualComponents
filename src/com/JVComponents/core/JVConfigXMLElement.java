package com.JVComponents.core;

import org.dom4j.*;
import java.util.*;

public class JVConfigXMLElement extends JVAbstractComponent {

	/**
	 * ��Ӧ�Ľڵ�
	 */
	private Element element;

	public Element getElement() {
		return element;
	}

	/**
	 * @return
	 * 
	 * 		���ڷ�װ�Ķ���
	 * 
	 */
	protected Object getPackagedObject() {
		return element;
	}

	/**
	 * �����ýڵ��XML�ļ�����
	 */
	private JVConfigXMLFile configXMLFile;

	public JVConfigXMLFile getConfigXMLFile() {
		return configXMLFile;
	}

	/**
	 * �ӽڵ㼯��
	 */
	private HashSet<JVConfigXMLElement> subElements;

	public Iterator<JVConfigXMLElement> getElementsIterator() {
		return subElements.iterator();
	}

	/**
	 * ���ӽڵ��б�������һ���ӽڵ�
	 * 
	 * @param element
	 * @throws JVException
	 * 
	 */
	public JVConfigXMLElement addSubElement(Element element) throws JVException {
		// �Ȳ��ң����������ֱ�ӷ��أ����򴴽�һ��
		JVConfigXMLElement result = findSubElement(element);

		if (result == null) {
			// ͨ�����������ӽڵ㣬������Լ̳в����ز�ͬ���͵Ľڵ����
			result = createSubElement(element);
			subElements.add(result);
		}
		return result;
	}

	/**
	 * �����ӽڵ㺯����������Լ̳з��ز�ͬ���͵Ľڵ����
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLElement createSubElement(Element element) throws JVException {
		// ����ӽڵ㻹���ӽڵ���ݹ鴴��
		return new JVConfigXMLElement(configXMLFile, element);
	}

	/**
	 * 
	 * ���ݴ���Ľڵ���ҽڵ����
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
	 * �ڵ����Լ���
	 */
	private HashSet<JVConfigXMLAttribute> attributes;
	public Iterator<JVConfigXMLAttribute> getAttributesIterator() {
		return attributes.iterator();
	}
	
	public JVConfigXMLAttribute addAttribute(Attribute attribute) throws JVException {
		// �Ȳ��ң����������ֱ�ӷ��أ����򴴽�һ��
		JVConfigXMLAttribute result = null;
		
		if (result == null) {
			// ͨ�������������ԣ�������Լ̳в����ز�ͬ���͵����Զ���
			result = createAttribute(attribute);
			attributes.add(result);
		}
		
		return result;		
	}
	
	/**
	 * �������Ժ�����������Լ̳з��ز�ͬ���͵����Զ���
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
	 * ���ݴ�������Բ������Զ���
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
	 * ���ݴ�����������Ʋ������Զ���
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
		//�ýڵ�����
		super(element.getName());

		this.configXMLFile = configXMLFile;
		this.element = element;

		// �ӽڵ㼯��
		this.subElements = new HashSet<JVConfigXMLElement>();

		// ��ȡÿ���ӽڵ㲢���뵽�ӽڵ㼯����
		Iterator<Element> eiter = element.elementIterator();
		while (eiter.hasNext()) {
			addSubElement(eiter.next());
		}

		// ���Լ���
		this.attributes = new HashSet<JVConfigXMLAttribute>();
		// ��ȡÿ�����Բ����뵽���Լ�����
		Iterator<Attribute> aiter = element.attributeIterator();
		while (aiter.hasNext()) {
			addAttribute(aiter.next());
		}
	}

}
