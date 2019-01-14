package com.JVComponents.core;

public class JVShoutCutItem extends JVListItem {
	
	/**
	 * 快捷键
	 */
	private JVPropertyString key;
	
	/**
	 * 功能键 
	 */
	private JVShoutCutKeyEnum speKey;

	public JVShoutCutItem(String name, JVirtualList list) throws JVException {
		super(name, list);
		
		speKey = JVShoutCutKeyEnum.Ctrl;
		//缺省快捷键
		key = new JVPropertyString(this, "1");
	}

	public JVPropertyString getKey() {
		return key;
	}

	public JVShoutCutKeyEnum getSpeKey() {
		return speKey;
	}
}
