package com.JVComponents.Plugin;

import java.util.Iterator;

import org.dom4j.*;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

public class JVPluginXMLFile extends JVConfigXMLFile {

	/**
	 * 
	 * 返回根节点名称
	 *  Plugin.xml有一个根节点，且为'plugin'
	 * 
	 * @return
	 */
	@Override
	public String getRootName() {
		return JVPluginConsts.JVPluginRoot.rootName;
	}

	public JVPluginXMLFile(String name, String filename) throws JVException {
		super(name, filename);
	}
	
	/**
	 * 根据节点查找扩展点
	 * 
	 * @param element
	 * @return
	 */
	private JVPluginExtension findExtension(Element element) {
		JVPluginExtension result = null;
		//读取节点上point属性值
		String str = element.attributeValue(JVPluginConsts.JVPluginRoot.point);
		if(str == null) {
			return result;
		}
		//在根节点对象中查找扩展对象，根据point属性具有唯一性
		Iterator<JVConfigXMLElement> iter = getRoot().getSubXMLElements().iterator();
		JVConfigXMLElement xmlElement;
		JVPluginExtension tmp;
		while (iter.hasNext()) {
			xmlElement = iter.next();
			//检查是否为扩展对象
			if(xmlElement instanceof JVPluginExtension) {
				tmp = (JVPluginExtension)xmlElement;
				if(str.equals(tmp.getPointValue())) {
					result = (JVPluginExtension)xmlElement;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 根据扩展对象类型查找扩展对象
	 * 
	 * @param extensionType
	 * @return
	 */
	public JVPluginExtension findExtension(Class<? extends JVPluginExtension> extensionType) {
		JVPluginExtension result = null;
		//在根节点对象中查找扩展对象
		Iterator<JVConfigXMLElement> iter = getRoot().getSubXMLElements().iterator();
		JVConfigXMLElement tmp;
		while(iter.hasNext()) {
			tmp = iter.next();
			if(tmp.getClass() == extensionType){
				result = (JVPluginExtension)tmp;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 根据节点创建节点对象，子类继承创建不同节点对象
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	@Override
	public JVConfigXMLElement createXMLElement(Element element) throws JVException{
		JVConfigXMLElement result = null;
		Element parentElement = element.getParent();
		//如果是根节点则由基类方法创建
		if (parentElement == null) {
			result = super.createXMLElement(element);
		}else {//通过工厂创建，创建过程中读取属性和子节点
			String str = parentElement.getName();
			//如果是第一级则是扩展节点
			if(str.equals(getRootName())) {
				//根据节点得到扩展对象类型
				Class<? extends JVPluginExtension> pluginClass = JVPluginExtensionFactory.getPluginExtensionClass(element);
				//在现有列表中查找，未找到则创建，找到了就共用
				result = this.findExtension(pluginClass);
				if(result == null) {
					result = JVPluginExtensionFactory.createPluginExtension(this, element);
				}
			}else {
				//先得到扩展对象
				JVPluginExtension extension = findExtension(parentElement);
				if(extension != null) {
					JVPluginExtensionFactory.createPluginElement(extension, element);
				}
			}
		}
		return result;
	}
	
	@Override
	public void readFromFile() throws JVException {
		super.readFromFile();
		
		//读入内存后需要根据内容进行对象配对
		Iterator<JVConfigXMLElement> iter = getRoot().getSubXMLElements().iterator();
		JVConfigXMLElement element;
		JVPluginExtension extension;
		//逐个进行匹配
		while(iter.hasNext()) {
			element = iter.next();
			if(element instanceof JVPluginExtension) {
				extension = (JVPluginExtension) element;
				extension.matchPluginElement();
			}
		}
		
	}
}
