package com.JVComponents.core;

/**
 * @author DELL
 * 
 * 所有非嵌入式组件的基类
 * 
 * 不能以组件形式放入容器进行可视化设计，但是能够出现在属性编辑器中
 *
 */
public class JVAbstractComponent extends JVComponent {

	public JVAbstractComponent(String name) throws JVException {
		super(name);
	}

}
