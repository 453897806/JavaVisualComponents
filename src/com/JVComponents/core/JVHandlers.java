package com.JVComponents.core;

public class JVHandlers extends JVirtualList {

	public JVHandlers(JVContainer container) throws JVException {
		super(container);
	}

	@Override
	public Class<?> getItemClass() {
		return JVHandlerItem.class;
	}
}
