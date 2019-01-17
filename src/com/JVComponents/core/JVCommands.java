package com.JVComponents.core;

/**
 * Action集合
 * 
 * @author bob
 *
 */
public class JVCommands extends JVirtualList {

	public JVCommands(JVContainer container) throws JVException {
		super(container);
	}

	@Override
	public Class<?> getItemClass() {
		return JVCommandItem.class;
	}
}
