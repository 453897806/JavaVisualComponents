package com.JVComponents.Plugin;

import org.dom4j.*;
import java.lang.reflect.Constructor;
import java.util.Iterator;

import com.JVComponents.Plugin.JVPluginConsts.JVPluginMenus;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVEmbedComponent;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVShoutcuts;
import com.JVComponents.core.JVirtualList;
import com.JVComponents.core.JVHandlers;
import com.JVComponents.core.JVListItem;
import com.JVComponents.core.JVAbstractComponent;
import com.JVComponents.core.JVCommands;
import com.JVComponents.core.JVConfigXMLElement;

/**
 * 
 * 扩展组件的构建工厂
 * 
 * @author bob
 *
 */
public class JVPluginFactory {

	public JVPluginFactory() {

	}

	/**
	 *  根据节点的名称创建扩展对象
	 *  
	 * @param pluginXMLFile
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public static JVPluginExtension createPluginExtension(JVPluginXMLFile pluginXMLFile, Element element)	throws JVException{
		JVPluginExtension result = null;

		//根据节点获取扩展对象类型
		Class<? extends JVPluginExtension> pluginClass = getPluginExtensionClass(element);

		//根据节点对象类型创建的扩展对象
		if(pluginClass != null) {
			try {
				Constructor<? extends JVPluginExtension> constructor = pluginClass.getConstructor(JVPluginXMLFile.class, Element.class);
				if(constructor != null) {
					result = (JVPluginExtension) constructor.newInstance(pluginXMLFile, element);
				}
			}catch(Exception e) {
				throw new JVException("创建扩展对象失败！", e);
			}
		}
		//无法识别的节点忽略
		return result;
	}
	
	/**
	 * 根据节点内容判断节点的扩展对象类型 
	 * 
	 * @param element
	 * @return
	 */
	public static Class<? extends JVPluginExtension> getPluginExtensionClass(Element element){
		Class<? extends JVPluginExtension> result = null;
		// 根据节点名称创建不同的扩展，如果为空则返回
		Attribute attrPoint = element.attribute(JVPluginConsts.JVPluginRoot.point);
		if(attrPoint != null) {
			String strpoint = attrPoint.getValue();
			// commands扩展
			if (strpoint.equals(JVPluginConsts.JVPluginCommands.extensionCommands)) {
				result = JVPluginExtensionCommands.class;
			}
			// Menus扩展
			else if (strpoint.equals(JVPluginConsts.JVPluginMenus.extensionMenus)) {
				result = JVPluginExtensionMenus.class;
			}
			// Bindings扩展
			else if (strpoint.equals(JVPluginConsts.JVPluginBindings.extensionBindings)) {
				result = JVPluginExtensionBindings.class;
			}
			// Handlers扩展
			else if (strpoint.equals(JVPluginConsts.JVPluginHandlers.extensionHandlers)) {
				result = JVPluginExtensionHandlers.class;
			}
		}
		return result;
	}
	
	/**
	 * 根据节点内容判断节点对象类型
	 * 
	 * @param element
	 * @return
	 */
	public static Class<? extends JVPluginElement> getPluginElementClass(String elementName){
		Class<? extends JVPluginElement> result = null;
		
		if (elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommand.command)) {
			result = JVPluginElementCommand.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommandCategory.category)) {
			result = JVPluginElementCategory.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginBindings.JVPluginKey.key)) {
			result = JVPluginElementKey.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.menuContribution)) {
			result = JVPluginElementMenuContribution.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginHandlers.JVPluginHandler.handler)) {
			result = JVPluginElementHandler.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenu.menu)) {
			result = JVPluginElementMenu.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand)) {
			result = JVPluginElementMenuCommand.class;
		} else if (elementName.equals(JVPluginConsts.JVPluginMenus.JVPluginToolbar.toolbar)) {
			result = JVPluginElementToolbar.class;
		} 
		
		return result;
	}

	/**
	 * 根据节点类型创建节点
	 * 
	 * @param extension
	 * @param elementType
	 * @return
	 * @throws JVException
	 */
	public static JVPluginElement createPluginElement(JVPluginXMLFile pluginXMLFile, Element element)throws JVException{
		JVPluginElement result = null;
		String elementName =  element.getName();
		//特殊情况，在Menu下的command是menuCommand
		if(elementName.equals(JVPluginConsts.JVPluginCommands.JVPluginCommand.command)) {
			
			Element parentElement = element.getParent();
			String parentName = parentElement.getName();
			if(!parentName.equals(JVPluginConsts.JVPluginRoot.extension)) {
				elementName = JVPluginConsts.JVPluginMenus.JVPluginMenu.menuCommand;
			}
		}
		
		//根据节点获取节点对象类型
		Class<? extends JVPluginElement> pluginClass = getPluginElementClass(elementName);
		
		//根据节点对象类型创建节点对象
		if(pluginClass != null) {
			try {
				Constructor<? extends JVPluginElement> constructor = pluginClass.getConstructor(
						JVConfigXMLFile.class, Element.class);
				if(constructor != null) {
					result = (JVPluginElement) constructor.newInstance(pluginXMLFile, element);
				}
			}catch(Exception e) {
				throw new JVException("创建节点对象失败！", e);
			}
		}
		//无法识别的节点忽略
		return result;
	}
	
	/**
	 *  根据传入的扩展对象返回组件类
	 *  
	 * @param extension
	 * @return
	 */
	public static Class<? extends JVEmbedComponent> getJVEmbedComponentClass(JVPluginExtension extension){
		Class<? extends JVEmbedComponent> result = null;
		if(extension instanceof JVPluginExtensionBindings){
			result = com.JVComponents.core.JVShoutcuts.class;
		}else if(extension instanceof JVPluginExtensionCommands){
			result = com.JVComponents.core.JVCommands.class;
		}else if(extension instanceof JVPluginExtensionHandlers){
			result = com.JVComponents.core.JVHandlers.class;
		}
		
		return result;
	}

	/**
	 * 根据扩展对象创建列表对象
	 * 
	 * @param extension
	 * @return
	 */
	public static JVEmbedComponent createJVEmbedComponent(JVPluginExtension extension, JVContainer container) throws  JVException {
		JVEmbedComponent result = null;  
		
		//根据节点获取节点对象类型
		Class<? extends JVEmbedComponent> componentClass = getJVEmbedComponentClass(extension);

		//根据对象类型创建对象
		if(componentClass != null) {
			try {
				Constructor<? extends JVEmbedComponent> constructor = 
						componentClass.getConstructor(JVContainer.class);
				if(constructor != null) {
					result = (JVEmbedComponent) constructor.newInstance(container);
				}
			}catch(Exception e) {
				throw new JVException("创建组件对象失败！", e);
			}
		}
		
		return result;
	}

	/**
	 * 根据节点对象创建列表对象
	 * 
	 * @param element
	 * @param container
	 * @return
	 * @throws JVException
	 */
	public static JVEmbedComponent createJVEmbedComponent(JVConfigXMLElement element, JVContainer container) throws  JVException {
		JVEmbedComponent result = null;  
		
		//根据节点获取节点对象类型
		Class<? extends JVEmbedComponent> componentClass = getJVEmbedComponentClass(element);

		//根据对象类型创建对象
		if(componentClass != null) {
			try {
				Constructor<? extends JVEmbedComponent> constructor = 
						componentClass.getConstructor(JVContainer.class);
				if(constructor != null) {
					result = (JVEmbedComponent) constructor.newInstance(container);
				}
			}catch(Exception e) {
				throw new JVException("创建组件对象失败！", e);
			}
		}
		
		//构建子对象
		if((result != null) & (result instanceof JVirtualList)) {
			JVirtualList list = (JVirtualList)result;
			createVirtualList(list, element);
		}
		
		return result;
	}

	/**
	 * 根据节点构建子对象
	 * 
	 * @param list
	 * @param element
	 * @param container
	 * @throws JVException 
	 */
	private static void createVirtualList(JVirtualList list, JVConfigXMLElement element) throws JVException {
		Iterator<JVConfigXMLElement> iter = element.getSubXMLElements().iterator();
		JVConfigXMLElement tmp;
		JVListItem itm;
		while(iter.hasNext()) {
			tmp = iter.next();
			//类型相同则创建
			Class<? extends JVAbstractComponent> cmClass = getJVAbstractComponentClass(tmp);
			if(list.getItemClass() == cmClass) {
				itm = list.createListItem();
				//更新子对象内容
				updateListItem(itm, tmp);
			}
		}		
	}

	/**
	 * 根据节点对象更新组件对象
	 * 
	 * @param itm
	 * @param tmp
	 */
	private static void updateListItem(JVListItem itm, JVConfigXMLElement tmp) {
		//根据节点返回对象类型
		if(itm instanceof com.JVComponents.core.JVCommandItem) {
		}else if(itm instanceof com.JVComponents.core.JVHandlerItem) {
		}else if(itm instanceof com.JVComponents.core.JVMenuItem) {
		}
	}

	/**
	 * 根据传入的节点对象返回组件类
	 * 
	 * @param element
	 * @return
	 * @throws JVException 
	 */
	private static Class<? extends JVEmbedComponent> getJVEmbedComponentClass(JVConfigXMLElement element) throws JVException {
		Class<? extends JVEmbedComponent> result = null;
		//菜单分布节点对象
		if(element instanceof JVPluginElementMenuContribution){
			JVPluginElementMenuContribution mc = (JVPluginElementMenuContribution)element;
			String uri = (String)mc.getLocationURI().getValue().getValue();
			
			//主菜单
			if(uri.indexOf(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.locationURI_MainMemu) >= 0) {
				result = com.JVComponents.Plugin.JVPluginMainMenus.class;
			}else//主工具栏 
				if(uri.indexOf(JVPluginConsts.JVPluginMenus.JVPluginMenuContribution.locationURI_MainToolbar) >= 0) {
				result = com.JVComponents.Plugin.JVPluginMainToolbars.class;
			}	
		}
		return result;
	}
	
	/**
	 * 根据传入的节点对象返回组件类
	 * 
	 * @param element
	 * @return
	 * @throws JVException 
	 */
	private static Class<? extends JVAbstractComponent> getJVAbstractComponentClass(JVConfigXMLElement element) throws JVException {
		Class<? extends JVAbstractComponent> result = null;
		//根据节点返回对象类型
		if(element instanceof JVPluginElementCommand) {
			result = com.JVComponents.core.JVCommandItem.class;
		}else if(element instanceof JVPluginElementHandler) {
			result = com.JVComponents.core.JVHandlerItem.class;
		}else if(element instanceof JVPluginElementMenu) {
			result = com.JVComponents.core.JVMenuItem.class;
		}else if(element instanceof JVPluginElementToolbar) {
			result = com.JVComponents.core.JVMenuItem.class;
		}
		return result;
	}
}
