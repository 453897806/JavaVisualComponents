package com.JVComponents.Plugin;

import org.dom4j.*;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLElement;
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
	public static JVPluginExtension createPluginExtension(JVPluginXMLFile pluginXMLFile, JVConfigXMLElement element)
			throws JVException {
		JVPluginExtension result;

		// 根据节点名称创建不同的扩展
		JVConfigXMLAttribute attrPoint = element.findAttribute(JVPluginConsts.JVPluginRoot.point);
		String strpoint = (String) attrPoint.getName().getValue();

		// commands扩展
		if (strpoint.equals(JVPluginConsts.JVPluginCommands.extensionCommands)) {
			result = new JVPluginExtensionCommands(pluginXMLFile, element);
		}
		// Menus扩展
		else if (strpoint.equals(JVPluginConsts.JVPluginMenus.extensionMenus)) {
			result = new JVPluginExtensionMenus(pluginXMLFile, element);
		}
		// Bindings扩展
		else if (strpoint.equals(JVPluginConsts.JVPluginBindings.extensionBindings)) {
			result = new JVPluginExtensionBindings(pluginXMLFile, element);
		}
		// Handlers扩展
		else if (strpoint.equals(JVPluginConsts.JVPluginHandlers.extensionHandlers)) {
			result = new JVPluginExtensionHandlers(pluginXMLFile, element);
		} else {
			throw new JVException("无法识别的扩展点", null);
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
	public static JVPluginElement createPluginElement(JVPluginExtension extension, Element element)
			throws JVException {
		JVPluginElement result = null;
		String elementName =  element.getName();
		
		if (elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommand.command)) {
			result = new JVPluginElementCommand(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommandCategory.category)) {
			result = new JVPluginElementCategory(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginBindings.JVPluginKey.key)) {
			result = new JVPluginElementKey(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.menuContribution)) {
			result = new JVPluginElementMenuContribution(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginHandlers.JVPluginHandler.handler)) {
			result = new JVPluginElementHandler(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenu.menu)) {
			result = new JVPluginElementMenu(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand)) {
			result = new JVPluginElementMenuCommand(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginToolbar.toolbar)) {
			result = new JVPluginElementToolbar(extension, element);
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginToolbar.toolbarCommand)) {
			result = new JVPluginElementToolbarCommand(extension, element);
		}
		//无法识别的节点忽略

		return result;
	}

}
