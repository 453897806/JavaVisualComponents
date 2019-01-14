package com.JVComponents.core;

public class JVShoutCutList extends JVirtualList {

	public JVShoutCutList(JVContainer container) throws JVException {
		super(container);
	}

	@Override
	public Class<?> getItemClass() {
		return JVShoutCutItem.class;
	}
}
