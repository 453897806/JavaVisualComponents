package com.JVComponents.Plugin;

public final class JVPluginConsts {

	/**
	 * plugin.XML的根节点定义
	 * 
	 * @author bob
	 *
	 */
	public final class JVPluginRoot {
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
		 * 扩展的扩展点属性
		 */
		public static final String point = "point";

		/**
		 * 属性id
		 */
		public static final String id = "id";

		/**
		 * 属性name
		 */
		public static final String name = "name";

		/**
		 * 属性class
		 */
		public static final String attr_class = "class";
	}

	public final class JVPluginCommands {

		/**
		 * commands扩展
		 */
		public static final String extensionCommands = "org.eclipse.ui.commands";

		public final class JVPluginCommandCategory {
			/**
			 * commands扩展下的category
			 */
			public static final String category = "category";

			/**
			 * 属性categoryId
			 */
			public static final String categoryId = "categoryId";
		}

		public final class JVPluginCommand {
			/**
			 * commands扩展下的command
			 */
			public static final String command = "command";

			/**
			 * 属性commandId
			 */
			public static final String commandId = "commandId";
		}
	}

	public final class JVPluginBindings {

		/**
		 * bindings扩展
		 */
		public static final String extensionBindings = "org.eclipse.ui.bindings";

		public final class JVPluginKey {
			/**
			 * bindings扩展下的key
			 */
			public static final String key = "key";

			public static final String schemeId = "schemeId";

			public static final String schemeId_value = "org.eclipse.ui.defaultAcceleratorConfiguration";

			public static final String contextId = "contextId";
			public static final String contextId_value = "org.eclipse.ui.contexts.window";

			public static final String sequence = "sequence";
			public static final String sequence_value = "M1+6";
		}

	}

	public final class JVPluginMenus {
		/**
		 * menus扩展
		 */
		public static final String extensionMenus = "org.eclipse.ui.menus";
		
		/**
		 * 属性icon
		 */
		public static final String icon = "icon";

		public final class JVPluginMenuContribution {
			/**
			 * menus扩展下的menuContribution
			 */
			public static final String menuContribution = "menuContribution";

			/**
			 * locationURL属性 menu:org.eclipse.ui.main.menu?after=additions
			 * 
			 * 该属性被分成三个部分: scheme(模式):标识被添加的UI对象的类型，取值如下： menu 程序的主菜单或视图下拉菜单 popup
			 * 视图或编辑器的上下文菜单 toolbar 程序的主工具栏或视图的工具栏
			 * identifier(标识符):定义了UI对象被添加到的位置（如菜单、工具栏）的唯一标识符，常见的包括： 
			 * org.eclipse.ui.main.menu  		主菜单 
			 * org.eclipse.ui.main.toolbar 	主工具栏 
			 * org.eclipse.ui.popup.any  		任意的上下文菜单，即所有视图和编辑器的上下文菜单中
			 * 
			 * argumentList(参数列表)：指定被添加的UI对象所在位置的具体细化参照对象定义 before/after 在指定的参照对象前或后 =
			 * 具体的参照对象 additions
			 */
			public static final String locationURI = "locationURI";
			public static final String locationURI_MainMemu = "org.eclipse.ui.main.menu";
			public static final String locationURI_MainToolbar = "org.eclipse.ui.main.toolbar";
			public static final String locationURI_PopupAny = "org.eclipse.ui.popup.any";
		}

		public final class JVPluginMenu {
			/**
			 * menus扩展下的menu
			 */
			public static final String menu = "menu";
			public static final String menuCommand = "menuCommand";
			public static final String label = "label";
			public static final String mnemonic = "mnemonic";
			public static final String mnemonic_value = "M";
		}

		public final class JVPluginToolbar {
			/**
			 * menus扩展下的toolbar
			 */
			public static final String toolbar = "toolbar";
			public static final String toolbarCommand = "toolbarCommand";
			public static final String tooltip = "tooltip";
		}
	}

	public final class JVPluginHandlers {
		/**
		 * handlers扩展
		 */
		public static final String extensionHandlers = "org.eclipse.ui.handlers";

		public final class JVPluginHandler {
			/**
			 * handlers扩展下的handler
			 */
			public static final String handler = "handler";
		}
	}
}
