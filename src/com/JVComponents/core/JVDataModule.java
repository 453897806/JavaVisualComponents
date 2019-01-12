package com.JVComponents.core;

/**
 * 数据模块，用于容纳不可见组件
 * 
 * @author DELL
 *
 */
public class JVDataModule extends JVContainer {

	public JVDataModule(String name) throws JVException {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 加入一个组件
	 * 
	 * 数据模块由于只能容纳不可见组件，所以需要进行验证
	 * 
	 * @param component
	 * @throws JVException 
	 * 
	 */
	public void addCompnent(JVEmbedComponent component) throws JVException {
		//验证组件
		if(component instanceof JVirtualComponent) {
			//继承父类		
			super.addCompnent(component);
		}else {
			throw new JVException("非虚拟化组件及其子类不能加入数据模块。", null);
		}
	}
}
