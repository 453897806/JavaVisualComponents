package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;

import java.util.Iterator;

import org.dom4j.Element;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVException;

/**
 * 
 * 以虚拟组件形式封装了plugin下的扩展Extension
 * 
 * @author DELL
 *
 */
public abstract class JVPluginExtension extends JVConfigXMLElement {

	public JVPluginExtension(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}

	/**
	 * 对应的plugin文件
	 * 
	 * @throws JVException
	 */
	public JVPluginXMLFile getPluginFile() throws JVException {
		// 检查
		if (!(getConfigXMLFile() instanceof JVPluginXMLFile)) {
			throw new JVException("不是Plugin的XML文件！", null);
		}

		return (JVPluginXMLFile) getConfigXMLFile();
	}

	/**
	 * 
	 * 子类继承并返回扩展点名称
	 * 
	 * @return
	 */
	public abstract String getPointValue();

	/**
	 * 得到point属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getPoint() throws JVException {
		return getXMLAttribute(JVPluginConsts.JVPluginRoot.point, getPointValue());
	}

	/**
	 * 根据扩展下各个节点内容进行对象配对
	 *  
	 */
	public void matchPluginElement() throws JVException {
			//command对象要匹配category对象
			Iterator<JVConfigXMLElement> iter = this.getSubXMLElements().iterator();
			JVConfigXMLElement xmlElement;
			JVPluginElement element;
			while(iter.hasNext()) {
				xmlElement = iter.next();
				if(xmlElement instanceof JVPluginElement) {
					element = (JVPluginElement)xmlElement;
					element.matchPluginElement();
				}
			}	
	}
}
