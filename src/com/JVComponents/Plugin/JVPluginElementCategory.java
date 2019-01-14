package com.JVComponents.Plugin;

import com.JVComponents.core.JVException;

/**
 * 	命令分组定义节点
 * 
 * @author bob
 *
 */
public class JVPluginElementCategory extends JVPluginElement {

	public JVPluginElementCategory(JVPluginExtension extension) throws JVException {
		super(extension);
	}

	@Override
	public String getElementType() {
		return JVPluginConsts.category;
	}
	
}
