package com.JVComponents.core;

/**
 * @author DELL
 * 
 * 所有可视化控件的基类，在容器和运行期可见
 *
 */
public class JVisualComponent extends JVEmbedComponent {
	


	public JVisualComponent(JVContainer container) throws JVException {
		super(container);
		
		//构建属性
		visible = new JVPropertyBoolean(this, true);
	}
	
	
	/**
	 *	是否可见 
	 */
	private JVPropertyBoolean visible;
	public JVPropertyBoolean getVisible() {
		return visible;
	} 
	

}
