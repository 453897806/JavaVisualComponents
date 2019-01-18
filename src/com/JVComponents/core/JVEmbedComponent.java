package com.JVComponents.core;

public class JVEmbedComponent extends JVComponent {

	/**
	 * 可视化组件所在的容器
	 * 
	 */
	private JVContainer container;
	
	public JVContainer getContainer() {
		return container;
	}

	/**
	 * 构建函数，需要指定容器
	 * 
	 * 缺省的组件名称由容器生成
	 */
	
	public JVEmbedComponent(JVContainer container) throws JVException {
		//先用组件的类名做组件名
		super(JVConsts.componentDefualtName);
		
		//容器
		this.container = container; 

		//由容器生成组件名称
		container.addCompnent(this);
	}

}
