package com.JVComponents.core;

/**
 * Action类
 * 
 * @author bob
 *
 */
public class JVCommandItem extends JVListItem {
	
	/**
	 * Action的快捷键
	 */
	private JVShoutcutItem shoutCutItem;

	public JVShoutcutItem getShoutCutItem() {
		return shoutCutItem;
	}

	public void setShoutCutItem(JVShoutcutItem shoutCutItem) {
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

	public JVCommands getActionlist() {
		return (JVCommands) getList();
	}

	public JVCommandItem(String name, JVirtualList list) throws JVException {
		super(name, list);
		
		//成员
		caption = new JVPropertyString(this, name);
	}

}
