package com.JVComponents.Plugin;

import java.util.*;

import com.JVComponents.core.JVConfigXMLAttribute;
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
		return JVPluginConsts.rootName;
	}

	public JVPluginXMLFile(String name, String filename) throws JVException {
		super(name, filename);
	}
	
	/**
	 * 根据节点对象创建扩展对象
	 * 
	 * @return
	 * @throws JVException 
	 */
	private JVPluginExtension createExtension(JVConfigXMLElement element) throws JVException {
		JVPluginExtension result = JVPluginExtensionFactory.createPluginExtension(this, element);
		//加入扩展点
		addCompnent(result);
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
		
		//创建每一个扩展点
		JVConfigXMLElement root = getRoot();
		Iterator<JVConfigXMLElement> iter = root.getElementsIterator();
		while(iter.hasNext()) {
			createExtension(iter.next());
		}
	}
	
	

}
