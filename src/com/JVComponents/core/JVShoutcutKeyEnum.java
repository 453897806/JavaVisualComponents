package com.JVComponents.core;

/**
 * 快捷键的功能键定义  
 * 
 * @author bob
 *
 */
public enum JVShoutcutKeyEnum {
	 
	 Ctrl("Ctrl","M1"), 
	 Shift("Shift","M2"), 
	 Alt("Alt","M3");
	
	private final String keyName;
	private final String alias;
	
	private JVShoutcutKeyEnum(String keyName, String alias) {
		this.keyName = keyName;
		this.alias = alias;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getAlias() {
		return alias;
	}
}
