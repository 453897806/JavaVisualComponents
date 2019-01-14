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
	public static final String id = "id";
	/**
	 * 属性name
	 */
	public static final String name = "name";
	
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
	public static final String extensionBindings = "org.eclipse.ui.bindings";
	
	/**
	 * bindings扩展下的key
	 */
	public static final String key = "key";
	
	public static final String schemeId = "schemeId";
	public static final String schemeId_value = "org.eclipse.ui.defaultAcceleratorConfiguration";
	public static final String contextId = "contextId";
	public static final String contextId_value = "org.eclipse.ui.contexts.window";
	public static final String sequence = "sequence";
	
	/**
	 *  menus扩展
	 */
	public static final String extensionMenus = "org.eclipse.ui.menus";
	
	/**
	 * menus扩展下的menuContribution
	 */
	public static final String menuContribution = "menuContribution";
	
	public static final String locationURI = "locationURI";
	
	/**
	 * menus扩展下的menu
	 */
	public static final String menu = "menu";
	public static final String menuCommand = "menuCommand";
	public static final String label = "label";
	public static final String mnemonic = "mnemonic";
	public static final String icon = "icon";

	/**
	 * menus扩展下的toolbar
	 */
	public static final String toolbar = "toolbar";
	public static final String toolbarCommand = "toolbarCommand";
	public static final String tooltip = "tooltip";
	
	
	/**
	 *  handlers扩展
	 */
	public static final String extensionHandlers = "org.eclipse.ui.handlers";
	
	/**
	 * handlers扩展下的handler
	 */
	public static final String handler = "handler";
	
	public static final String attr_class = "class";
	public static final String commandId = "commandId";

}
