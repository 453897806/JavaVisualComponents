package com.JVComponents.Plugin;

import java.util.HashSet;
import java.util.Iterator;

import org.dom4j.*;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVEmbedComponent;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVirtualList;

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
				Class<? extends JVPluginExtension> pluginClass = JVPluginFactory.getPluginExtensionClass(element);
				//在现有列表中查找，未找到则创建，找到了就共用
				result = this.findExtension(pluginClass);
				if(result == null) {
					result = JVPluginFactory.createPluginExtension(this, element);
				}
			}else {
				result = JVPluginFactory.createPluginElement(this, element);
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
	
	/**
	 * 由plugin对象创建可视化对象
	 * @throws JVException 
	 */
	public void createComponentsByPluginXML(JVContainer container) throws JVException {
		/*
		 * container中原有组件由调用者负责删除，这里只负责创建
		 *  依次读取扩展对象，创建对应的组件
		 *  
		 */
		//组件列表
		HashSet<JVEmbedComponent> embedComponents  = new HashSet<JVEmbedComponent>();
		
		//扩展对象列表
		Iterator<JVConfigXMLElement> iter = getRoot().getSubXMLElements().iterator();
		JVPluginExtension extension;
		while(iter.hasNext()) {
			extension = (JVPluginExtension)iter.next();
			//区分不同的菜单对象
			if(extension instanceof JVPluginExtensionMenus) {
				JVPluginExtensionMenus tmpMenus = (JVPluginExtensionMenus)extension;
				Iterator<JVConfigXMLElement> iterMenus = tmpMenus.getSubXMLElements().iterator();
				JVConfigXMLElement element;
				while(iterMenus.hasNext()) {
					element = iterMenus.next();
					//通过工厂创建组件对象
					embedComponents.add(JVPluginFactory.createJVEmbedComponent(element, container));
				}
			}else {
				//通过工厂创建组件对象
				embedComponents.add(JVPluginFactory.createJVEmbedComponent(extension, container));
			}
		}
		
		//关联对象关系
		incidenceComponentsRelation(embedComponents);
	}

	/**
	 * 在组件之间建立关联关系
	 * 
	 * @param embedComponents
	 */
	private void incidenceComponentsRelation(HashSet<JVEmbedComponent> embedComponents) {
		Iterator<JVEmbedComponent> iter = embedComponents.iterator();
		JVEmbedComponent tmp;
		while(iter.hasNext()) {
			tmp = iter.next();
			
		}
	}
}
