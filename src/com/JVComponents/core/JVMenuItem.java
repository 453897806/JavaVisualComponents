package com.JVComponents.core;

/**
 * 菜单类
 * 
 * @author bob
 *
 */
public class JVMenuItem extends JVListItem {
	
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
	 * 该菜单对应的执行代码
	 */
	private JVActionItem actionItem;
	
	public JVActionItem getActionItem() {
		return actionItem;
	}

	public void setActionItem(JVActionItem actionItem) throws JVException {
		if(this.actionItem != actionItem) {
			this.actionItem = actionItem;
			//复制Action的图片和caption属性
			this.image = actionItem.getImage();
			this.caption.setValue(actionItem.getCaption().getValue());
		}
	}

	/**
	 * 关联的记忆键
	 */
	private JVPropertyString mnemonic;

	public JVPropertyString getMnemonic() {
		return mnemonic;
	}

	/**
	 * 菜单显示标题
	 */
	private JVPropertyString caption;
	
	public JVPropertyString getCaption() {
		return caption;
	}
	
	public JVMenuList getMenulist() {
		return (JVMenuList) getList();
	}

	public JVMenuItem(String name, JVirtualList list) throws JVException {
		super(name, list);
		
		//成员
		this.caption = new JVPropertyString(this, name);
		//记忆键
		this.mnemonic = new JVPropertyString(this, JVConsts.emptyString);
	}

}
