package com.JVComponents.Plugin;

import java.util.Iterator;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

/**
 * Commands扩展
 * 
 * @author DELL
 *
 */
public class JVPluginExtensionCommands extends JVPluginExtension {

	public JVPluginExtensionCommands(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}
	
	@Override
	public String getPointValue() {
		return JVPluginConsts.JVPluginCommands.extensionCommands;
	}
	
	/**
	 * 根据categoryId查找category对象
	 * 
	 * @param categoryId
	 * @return
	 * @throws JVException
	 */
	public JVPluginElementCategory findCategory(String categoryId) throws JVException{
		JVPluginElementCategory result = null;
		
		Iterator<JVConfigXMLElement> iter = getSubXMLElements().iterator();
		JVConfigXMLElement xmlElement;
		JVPluginElementCategory category;
		while(iter.hasNext()) {
			xmlElement = iter.next();
			if(xmlElement instanceof JVPluginElementCategory) {
				category = (JVPluginElementCategory)xmlElement;
				if(categoryId.equals((String)category.getId().getValue().getValue())) {
					result = category;
					break;
				}
			}
		}
		
		return result;		
	}
	
	/**
	 * 根据commandId查找command对象
	 * 
	 * @param commandId
	 * @return
	 * @throws JVException
	 */
	public JVPluginElementCommand findCommand(String commandId) throws JVException{
		JVPluginElementCommand result = null;
		
		Iterator<JVConfigXMLElement> iter = getSubXMLElements().iterator();
		JVConfigXMLElement xmlElement;
		JVPluginElementCommand command;
		while(iter.hasNext()) {
			xmlElement = iter.next();
			if(xmlElement instanceof JVPluginElementCommand) {
				command = (JVPluginElementCommand)xmlElement;
				if(commandId.equals((String)command.getId().getValue().getValue())) {
					result = command;
					break;
				}
			}
		}
		
		return result;		
	}
}
