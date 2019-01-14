package com.JVComponents.core;

/**
 * 所有菜单集合的基类
 * 
 * @author bob
 *
 */
public class JVMenuList extends JVirtualList {

	public JVMenuList(JVContainer container) throws JVException {
		super(container);
	}

	@Override
	public Class<?> getItemClass() {
		return JVMenuItem.class;
	}

}
