package com.JVComponents.core;

/**
 * @author JVC
 *
 *	所有java可视化组件的基类
 */
public class JVComponent {
	
	/**
	 * 组件名称
	 * 在容器中应与组件的变量名称相同
	 */
	private JVPropertyString name;
	public JVPropertyString getName() {
		return name;
	}
	
	/**
	 * 构造函数
	 * @throws JVException 
	 */
	public JVComponent(String name) throws JVException {
		super();
		
		//构建属性
		this.name = new JVPropertyString(this, name);		
	}
	
	/**
	 * @return
	 * 
	 * 用于封装的对象
	 * 
	 * 通过抽象函数在子类继承后由子类创建得到
	 * 
	 */
	protected Object getPackagedObject(){
		return null;
	}
}
