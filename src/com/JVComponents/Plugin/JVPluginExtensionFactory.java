package com.JVComponents.Plugin;

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

	public static JVPluginExtension createPluginExtension(JVPluginXMLFile pluginXMLFile, JVConfigXMLElement element)
			throws JVException {
		JVPluginExtension result;

		// 根据节点名称创建不同的扩展
		JVConfigXMLAttribute attrPoint = element.findAttribute(JVPluginConsts.attributePoint);
		String strpoint = (String) attrPoint.getName().getValue();

		// commands扩展
		if (strpoint.equals(JVPluginConsts.extensionCommands)) {
			result = new JVPluginExtensionCommands(pluginXMLFile, element);
		}
		// Menus扩展
		else if (strpoint.equals(JVPluginConsts.extensionMenus)) {
			result = new JVPluginExtensionMenus(pluginXMLFile, element);
		}
		// Bindings扩展
		else if (strpoint.equals(JVPluginConsts.extensionBindings)) {
			result = new JVPluginExtensionBindings(pluginXMLFile, element);
		}
		// Handlers扩展
		else if (strpoint.equals(JVPluginConsts.extensionHandlers)) {
			result = new JVPluginExtensionHandlers(pluginXMLFile, element);
		} else {
			throw new JVException("无法识别的扩展点", null);
		}

		return result;
	}

	public static JVPluginElement createPluginElement(JVPluginExtension extension, String elementType)
			throws JVException {
		JVPluginElement result;

		if (elementType.equals(JVPluginConsts.command)) {
			result = new JVPluginElementCommand(extension);
		} else if (elementType.equals(JVPluginConsts.category)) {
			result = new JVPluginElementCategory(extension);
		} else if (elementType.equals(JVPluginConsts.key)) {
			result = new JVPluginElementKey(extension);
		} else if (elementType.equals(JVPluginConsts.menuContribution)) {
			result = new JVPluginElementMenuContribution(extension);
		} else if (elementType.equals(JVPluginConsts.menu)) {
			result = new JVPluginElementMenu(extension);
		} else if (elementType.equals(JVPluginConsts.handler)) {
			result = new JVPluginElementHandler(extension);
		} else {
			throw new JVException("无法识别的节点", null);
		}

		return result;
	}

}
