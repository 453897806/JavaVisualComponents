package com.JVComponents.core;

/**
 * @author DELL
 *
 *  虚拟化组件
 *  
 *  放置在容器中，在运行界面上不可见
 *  
 */
public class JVirtualComponent extends JVEmbedComponent {

	/**
	 * 构建函数，需要指定容器
	 * 
	 * 缺省的组件名称由容器生成
	 * 
	 * @throws JVException 
	 */
	public JVirtualComponent(JVContainer container) throws JVException {
		
		super(container);
		
	}


}
