package com.JVComponents.core;

/**
 * 非可视化图片类
 * 
 * @author bob
 *
 */
public class JVImageItem extends JVListItem {

	public JVImageList getImagelist() {
		return (JVImageList) getList();
	}	

	public JVImageItem(String name, JVirtualList list) throws JVException {
		super(name, list);
		
		//成员
		imageFile = new JVPropertyString(this, JVConsts.emptyString);
	}
	
	/**
	 * 图片文件名称
	 */
	private JVPropertyString imageFile ;

	public JVPropertyString getImageFile() {
		return imageFile;
	}
}
