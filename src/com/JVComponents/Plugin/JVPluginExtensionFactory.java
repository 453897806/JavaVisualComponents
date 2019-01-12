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

	public static JVPluginExtension createPluginExtension(JVPluginXMLFile pluginXMLFile, JVConfigXMLElement element) throws JVException {
		JVPluginExtension result;

		// 根据节点名称创建不同的扩展
		JVConfigXMLAttribute attrPoint = element.findAttribute(JVPluginConsts.attributePoint);
		String strpoint = (String) attrPoint.getName().getValue();

		// commands扩展
		if (strpoint.equals(JVPluginConsts.extensionCommands)) {
			result = new JVPluginExtensionCommands(pluginXMLFile, element);
		} else {
			throw new JVException("无法识别的扩展点", null);
		}

		return result;
	}

}
