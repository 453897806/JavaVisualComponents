package com.JVComponents.Plugin;

public interface JVPluginConsts {
	
	/**
	 * plugin.XML的根节点名称
	 */
	public static final String rootName = "plugin";
	
	
	/**
	 * 扩展节点名称
	 */
	public static final String extension = "extension";
	
	
	/**
	 * 扩展点节点名称
	 */
	public static final String extensionPoint = "extensionPoint";
	
	/**
	 * 属性id
	 */
	public static final String attributeId = "id";
	/**
	 * 属性name
	 */
	public static final String attributeName = "name";
	
	/**
	 * 属性categoryId
	 */
	public static final String categoryId = "categoryId";
	
	/**
	 * 扩展的扩展点属性
	 */
	public static final String attributePoint = "point";
	
	
	/**
	 *  commands扩展
	 */
	public static final String extensionCommands = "org.eclipse.ui.commands";
	
	/**
	 * commands扩展下的category
	 */
	public static final String category = "category";
	
	/**
	 * commands扩展下的command
	 */
	public static final String command = "command";
	
	/**
	 *  bindings扩展
	 */
	public static final String extensionBindings = "bindings";
	
	/**
	 * bindings扩展下的key
	 */
	public static final String key = "key";
	
	/**
	 *  menus扩展
	 */
	public static final String extensionMenus = "menus";
	
	/**
	 * menus扩展下的menuContribution
	 */
	public static final String menuContribution = "menuContribution";
	
	/**
	 * menus扩展下的menu
	 */
	public static final String menu = "menu";

	/**
	 *  handlers扩展
	 */
	public static final String extensionHandlers = "handlers";
	
	/**
	 * handlers扩展下的handler
	 */
	public static final String handler = "handler";

}
