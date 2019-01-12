package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVException;

public class JVPluginExtensionFactory {

	public JVPluginExtensionFactory() {

	}

	public static JVPluginExtension createPluginExtension(JVPluginXMLFile pluginXMLFile, JVConfigXMLElement element) throws JVException {
		JVPluginExtension result;

		// ���ݽڵ����ƴ�����ͬ����չ
		JVConfigXMLAttribute attrPoint = element.findAttribute(JVPluginConsts.attributePoint);
		String strpoint = (String) attrPoint.getName().getValue();

		// commands��չ
		if (strpoint.equals(JVPluginConsts.extensionCommands)) {
			result = new JVPluginExtensionCommands(pluginXMLFile, element);
		} else {
			throw new JVException("�޷�ʶ�����չ��", null);
		}

		return result;
	}

}
