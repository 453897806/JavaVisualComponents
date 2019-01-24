package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVConsts;

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
public abstract class JVPluginExtension extends JVPluginElement {
	
	private JVConfigXMLAttribute point;
	/**
	 * 得到point属性
	 * 
	 * @throws JVException 
	 */
	public JVConfigXMLAttribute getPoint() throws JVException {
		return point;
	}

	public JVPluginExtension(JVConfigXMLFile configXMLFile, Element element) throws JVException {
		super(configXMLFile, element);
	}

	/**
	 * 
	 * 子类继承并返回扩展点名称
	 * 
	 * @return
	 */
	public abstract String getPointValue();


	/**
	 * 针对节点读取属性对象，子类继承读取指定的属性
	 * 
	 * @param element
	 * @throws JVException
	 */
	protected void readAttributes(Element element) throws JVException {
		//忽略基类
		//super.readAttributes(element);
		//特殊属性
		point = getXMLAttribute(JVPluginConsts.JVPluginRoot.point, JVConsts.emptyString);
	}
	
	/**
	 * 根据扩展下各个节点内容进行对象配对
	 *  
	 */
	@Override
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
