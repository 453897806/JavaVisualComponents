package com.JVComponents.core;

/**
 * 以集合方式管理的组件基类
 * 
 * @author bob
 *
 */
public class JVListItem extends JVAbstractComponent {
	
	/**
	 * 所属的集合对象 
	 */
	private JVirtualList list;
	
	public JVirtualList getList() {
		return list;
	}

	public JVListItem(String name, JVirtualList list) throws JVException {
		super(name);
		
		this.list = list;
	}

}
