package com.JVComponents.core;

/**
 * 图片集合
 * 
 * @author bob
 *
 */
public class JVImages extends JVirtualList {

	public JVImages(JVContainer container) throws JVException {
		super(container);
	}
	
	@Override
	public Class<?> getItemClass() {
		return JVImageItem.class;
	}
}
