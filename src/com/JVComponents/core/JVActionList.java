package com.JVComponents.core;

/**
 * Action集合
 * 
 * @author bob
 *
 */
public class JVActionList extends JVirtualList {

	public JVActionList(JVContainer container) throws JVException {
		super(container);
	}

	@Override
	public Class<?> getItemClass() {
		return JVActionItem.class;
	}
}
