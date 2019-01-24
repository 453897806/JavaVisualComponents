package com.JVComponents.Plugin;

import org.dom4j.*;
import java.lang.reflect.Constructor;

import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

/**
 * 
 * 扩展组件的构建工厂
 * 
 * @author bob
 *
 */
public class JVPluginExtensionFactory {

	public JVPluginExtensionFactory() {

	}

	/**
	 *  根据节点的名称创建扩展对象
	 *  
	 * @param pluginXMLFile
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public static JVPluginExtension createPluginExtension(JVPluginXMLFile pluginXMLFile, Element element)	throws JVException{
		JVPluginExtension result = null;

		//根据节点获取扩展对象类型
		Class<? extends JVPluginExtension> pluginClass = getPluginExtensionClass(element);

		//根据节点对象类型创建的扩展对象
		if(pluginClass != null) {
			try {
				Constructor<? extends JVPluginExtension> constructor = pluginClass.getConstructor(JVPluginXMLFile.class, Element.class);
				if(constructor != null) {
					result = (JVPluginExtension) constructor.newInstance(pluginXMLFile, element);
				}
			}catch(Exception e) {
				throw new JVException("创建扩展对象失败！", e);
			}
		}
		//无法识别的节点忽略
		return result;
	}
	
	/**
	 * 根据节点内容判断节点的扩展对象类型 
	 * 
	 * @param element
	 * @return
	 */
	public static Class<? extends JVPluginExtension> getPluginExtensionClass(Element element){
		Class<? extends JVPluginExtension> result = null;
		// 根据节点名称创建不同的扩展，如果为空则返回
		Attribute attrPoint = element.attribute(JVPluginConsts.JVPluginRoot.point);
		if(attrPoint != null) {
			String strpoint = attrPoint.getValue();
			// commands扩展
			if (strpoint.equals(JVPluginConsts.JVPluginCommands.extensionCommands)) {
				result = JVPluginExtensionCommands.class;
			}
			// Menus扩展
			else if (strpoint.equals(JVPluginConsts.JVPluginMenus.extensionMenus)) {
				result = JVPluginExtensionMenus.class;
			}
			// Bindings扩展
			else if (strpoint.equals(JVPluginConsts.JVPluginBindings.extensionBindings)) {
				result = JVPluginExtensionBindings.class;
			}
			// Handlers扩展
			else if (strpoint.equals(JVPluginConsts.JVPluginHandlers.extensionHandlers)) {
				result = JVPluginExtensionHandlers.class;
			}
		}
		return result;
	}
	
	/**
	 * 根据节点内容判断节点对象类型
	 * 
	 * @param element
	 * @return
	 */
	public static Class<? extends JVPluginElement> getPluginElementClass(String elementName){
		Class<? extends JVPluginElement> result = null;
		
		if (elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommand.command)) {
			result = JVPluginElementCommand.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommandCategory.category)) {
			result = JVPluginElementCategory.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginBindings.JVPluginKey.key)) {
			result = JVPluginElementKey.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.menuContribution)) {
			result = JVPluginElementMenuContribution.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginHandlers.JVPluginHandler.handler)) {
			result = JVPluginElementHandler.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenu.menu)) {
			result = JVPluginElementMenu.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand)) {
			result = JVPluginElementMenuCommand.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginToolbar.toolbar)) {
			result = JVPluginElementToolbar.class;
		} 
		
		return result;
	}

	/**
	 * 根据节点类型创建节点
	 * 
	 * @param extension
	 * @param elementType
	 * @return
	 * @throws JVException
	 */
	public static JVPluginElement createPluginElement(JVPluginXMLFile pluginXMLFile, Element element)throws JVException{
		JVPluginElement result = null;
		String elementName =  element.getName();
		//特殊情况，在Menu下的command是menuCommand
		if(elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommand.command)) {
			
			Element parentElement = element.getParent();
			String parentName = parentElement.getName();
			if(!parentName.equals(JVPluginConsts.JVPluginRoot.extension)) {
				elementName = JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand;
			}
		}
		
		//根据节点获取节点对象类型
		Class<? extends JVPluginElement> pluginClass = getPluginElementClass(elementName);
		
		//根据节点对象类型创建节点对象
		if(pluginClass != null) {
			try {
				Constructor<? extends JVPluginElement> constructor = pluginClass.getConstructor(
						JVConfigXMLFile.class, Element.class);
				if(constructor != null) {
					result = (JVPluginElement) constructor.newInstance(pluginXMLFile, element);
				}
			}catch(Exception e) {
				throw new JVException("创建节点对象失败！", e);
			}
		}
		//无法识别的节点忽略
		return result;
	}

}
