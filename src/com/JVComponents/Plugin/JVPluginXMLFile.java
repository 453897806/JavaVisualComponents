package com.JVComponents.Plugin;

import java.util.*;
import org.dom4j.*;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVConsts;
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
		
		pluginExtensions = new HashSet<JVPluginExtension>();
	}
	
	private HashSet<JVPluginExtension> pluginExtensions;
	
	/**
	 * 扩展列表
	 * @return
	 */
	public HashSet<JVPluginExtension> getPluginExtensions() {
		return pluginExtensions;
	}

	/**
	 * 根据节点对象创建扩展对象
	 * 
	 * @return
	 * @throws JVException 
	 */
	private JVPluginExtension createExtension(JVConfigXMLElement element) throws JVException {
		//通过工厂创建，创建过程中读取，并自动加入容器内
		JVPluginExtension result = JVPluginExtensionFactory.createPluginExtension(this, element);
		
		//加入扩展点
		if(result != null) {
			pluginExtensions.add(result);
		}
		return result;
	}
		
	@Override
	public void readFromFile() throws JVException {
		super.readFromFile();
	
		// 检查根节点是否正确
		String str =(String)getRoot().getName().getValue(); 
		if (!str.equals(getRootName())) {
			throw new JVException("根节点不是<" + getRootName() + ">", null);
		}
		
		//根据节点创建扩展对象，在创建过程中即读取
		Iterator<Element> iter = getRoot().getElement().elementIterator();
		JVConfigXMLElement element ;
		while(iter.hasNext()) {
			element = new JVConfigXMLElement(this, iter.next(), JVConsts.componentDefualtName);
			createExtension(element);
		}
	}
	
	

}
