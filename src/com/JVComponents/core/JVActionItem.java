package com.JVComponents.core;

/**
 * Action类
 * 
 * @author bob
 *
 */
public class JVActionItem extends JVListItem {
	
	/**
	 * Action的快捷键
	 */
	private JVShoutCutItem shoutCutItem;

	public JVShoutCutItem getShoutCutItem() {
		return shoutCutItem;
	}

	public void setShoutCutItem(JVShoutCutItem shoutCutItem) {
		this.shoutCutItem = shoutCutItem;
	}

	/**
	 * 关联的图片
	 */
	private JVImageItem image;
	
	public JVImageItem getImage() {
		return image;
	}

	public void setImage(JVImageItem image) {
		this.image = image;
	}
	
	/**
	 * caption属性
	 */
	private JVPropertyString caption;
	
	public JVPropertyString getCaption() {
		return caption;
	}

	public JVActionList getActionlist() {
		return (JVActionList) getList();
	}

	public JVActionItem(String name, JVirtualList list) throws JVException {
		super(name, list);
		
		//成员
		caption = new JVPropertyString(this, name);
	}

}
