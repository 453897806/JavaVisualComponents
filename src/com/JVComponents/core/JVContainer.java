package com.JVComponents.core;

import java.util.*;

/**
 * @author DELL
 * 
 *         容器类组件基类，可以容纳其他可视化和虚拟化组件
 *
 */
public class JVContainer extends JVComponent {

	/**
	 * @author DELL
	 * 
	 *         私有类，侦听组件名称变化，防止组件名称重复
	 *
	 */
	private class ComponentNameChangedListener implements JVPropertyChangedListener {

		private JVContainer container;

		@Override
		public void handleEvent(JVPropertyChangedEvent event) throws JVException {
			// 检查传递参数是否正确
			if (!(event.getSource() instanceof JVPropertyString)) {
				throw new JVException("名字属性不是字符串属性！", null);
			}

			// 当组件名称变化时，需要检查组件名称是否与当前容器中的组件重复
			String newName = event.getNewValue().toString();
			if (container.checkComponentName(newName)) {
				// 如果重复，则抛出异常阻止名称修改
				throw new JVException("控件名字<" + newName + ">存在重复！", null);
			}
		}

		/**
		 * @param container
		 */
		public ComponentNameChangedListener(JVContainer container) {
			super();
			this.container = container;
		}
	}

	private ComponentNameChangedListener cmpNameChangeListeer;

	/**
	 * 容纳的全部组件
	 * 
	 */
	private HashSet<JVEmbedComponent> components;

	/**
	 * @return
	 * 
	 * 		容器中组件遍历
	 */
	public Iterator<JVEmbedComponent> getComponentsIterator() {
		return components.iterator();
	}

	/**
	 * 检查组件的名字在容器中是否唯一
	 * 
	 * @param component
	 * @return
	 * 
	 * 		true 存在相同名称的组件
	 * 
	 * @throws JVException
	 * 
	 */
	private Boolean checkComponentName(String name) throws JVException {
		// 缺省不存在
		Boolean result = false;

		// 遍历每个组件检查
		Iterator<JVEmbedComponent> iterator = getComponentsIterator();
		JVEmbedComponent tmp;
		String str;
		while (iterator.hasNext()) {
			tmp = iterator.next();

			// 取名称，如果相同则返回true
			str = tmp.getName().getValue().toString();
			if (str.equals(name)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * 获取类的直接名称，不包含前面的包名称
	 * 
	 * @param className
	 * @return
	 */
	private String getClassName(String className) {
		// 组件类名
		String result = className;
		Integer index = result.indexOf(".");
		while (index >= 0) {
			result = result.substring(index, result.length());
			index = result.indexOf(".");
		}

		return result;
	}

	/**
	 * 
	 * 根据组件获取在容器中的唯一名称
	 * 
	 * @param component
	 * @return
	 * @throws JVException
	 */
	private String getComponentName(JVEmbedComponent component) throws JVException {
		String result;

		// 组件类名
		String classname = getClassName(component.getClass().getName());
		// 编号从1开始
		Integer i = 1;
		// 循环，直到得到名字为止
		while (true) {
			// 用组件类名+序号做缺省名称
			result = classname + i.toString();

			// 检查名称是否存在，如果不存在表示名称唯一可以命名，否则编号加1再查
			if (checkComponentName(result)) {
				// 名称存在编号加1
				i++;
			} else
				// 没有找到则返回
				break;
		}
		return result;
	}

	/**
	 * 设置指定组件的名称
	 * 
	 * @param component
	 * @param newName
	 * @throws JVException
	 */
	private void setComponentName(JVEmbedComponent component, String newName) throws JVException {
		component.getName().setValue(newName);
	}

	/**
	 * 加入一个组件
	 * 
	 * @param component
	 * @throws JVException
	 * 
	 */
	public void addCompnent(JVEmbedComponent component) throws JVException {
		if (!components.contains(component)) {
			// 设置组件名称变化事件处理句柄
			component.getName().addListener(cmpNameChangeListeer);

			// 设置组件的名称，在容器内必须唯一
			String cmpname = component.getName().getValue().toString();

			// 如果存在相同名称的组件或名字为确实名称，则需要重新命名
			if (checkComponentName(cmpname) | cmpname.equals(JVConsts.componentDefualtName)) {
				// 重新获取名称
				cmpname = getComponentName(component);
				// 设置组件名称
				setComponentName(component, cmpname);
			}

			// 加入容器中
			components.add(component);
		}
	}

	public void removeComponent(JVEmbedComponent component) {
		components.remove(component);
	}

	public JVContainer(String name) throws JVException {
		super(name);

		// 容纳的全部组件
		components = new HashSet<JVEmbedComponent>();

		// 组件名称变化时的侦听，主要是防止组件名称重复
		cmpNameChangeListeer = new ComponentNameChangedListener(this);
	}

}
