package com.JVComponents.core;

/**
 * 图片集合
 * 
 * @author bob
 *
 */
public class JVImageList extends JVirtualList {

	public JVImageList(JVContainer container) throws JVException {
		super(container);
	}
	
	@Override
	public Class<?> getItemClass() {
		return JVImageItem.class;
	}
}
