package com.JVComponents.core;

public class JVShoutcuts extends JVirtualList {

	public JVShoutcuts(JVContainer container) throws JVException {
		super(container);
	}

	@Override
	public Class<?> getItemClass() {
		return JVShoutcutItem.class;
	}
}
