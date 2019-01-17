package com.JVComponents.core;

public class JVShoutcutItem extends JVListItem {
	
	/**
	 * 快捷键
	 */
	private JVPropertyString key;
	
	/**
	 * 功能键 
	 */
	private JVShoutcutKeyEnum speKey;

	public JVShoutcutItem(String name, JVirtualList list) throws JVException {
		super(name, list);
		
		speKey = JVShoutcutKeyEnum.Ctrl;
		//缺省快捷键
		key = new JVPropertyString(this, "1");
	}

	public JVPropertyString getKey() {
		return key;
	}

	public JVShoutcutKeyEnum getSpeKey() {
		return speKey;
	}
}
