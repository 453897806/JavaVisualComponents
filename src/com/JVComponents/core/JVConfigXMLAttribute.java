package com.JVComponents.core;

import org.dom4j.Attribute;

public class JVConfigXMLAttribute extends JVAbstractComponent {

	/**
	 *	包含此属性的节点 
	 */
	private JVConfigXMLElement element;
	public JVConfigXMLElement getElement() {
		return element;
	}
	
	/**
	 * 对应的属性对象
	 */
	private Attribute attribute;
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @return
	 * 
	 * 用于封装的对象
	 * 
	 */
	protected Object getPackagedObject(){
		return attribute;
	}
	
	/**
	 * 属性值
	 */
	private JVPropertyString value;
	public JVPropertyString getValue() {
		return value;
	}

	
	private class JVAttributeChangedListener implements JVPropertyChangedListener{
		
		private JVConfigXMLAttribute owner;

		@Override
		public void handleEvent(JVPropertyChangedEvent event) throws JVException {
			//当属性值变化时，需要同时写XML的属性值
			String str = (String)event.getNewValue();
			owner.getAttribute().setValue(str);						
		}

		public JVAttributeChangedListener(JVConfigXMLAttribute owner) {
			super();
			this.owner = owner;
		}
	}
	
	/**
	 * 根据属性名称创建一个属性对象
	 * 
	 * @param element
	 * @param attributeName
	 * @throws JVException
	 */
	public JVConfigXMLAttribute(JVConfigXMLElement element, String attributeName) throws JVException {
		super(attributeName);
		
		//成员
		this.element = element;	

		//根据名称取得属性对象，如果没有则创建
		this.attribute = element.getElement().attribute(attributeName);
		if(this.attribute == null) {
			element.getElement().addAttribute(attributeName, JVConsts.emptyString);
		}
		
		//属性值
		value = new JVPropertyString(this, this.attribute.getValue());
		//增加侦听，当属性值变化时，需要同时写XML的属性值
		value.addListener(new JVAttributeChangedListener(this));
	}
	
	public JVConfigXMLAttribute(JVConfigXMLElement element, Attribute attribute) throws JVException {
		super(attribute.getName());
		
		//成员
		this.element = element;	
		this.attribute = attribute;

		//属性值
		value = new JVPropertyString(this, this.attribute.getValue());
		//增加侦听，当属性值变化时，需要同时写XML的属性值
		value.addListener(new JVAttributeChangedListener(this));
	}

}
