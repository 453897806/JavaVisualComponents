package com.JVComponents.core;

import java.util.*;

import java.lang.reflect.*;

/**
 * 所有虚拟集合组件的基类
 * 
 * @author bob
 *
 */
public class JVirtualList extends JVirtualComponent {
	
	/**
	 *  私有类，侦听项目名称变化，防止项目名称重复
	 * 
	 * @author bob
	 * 
	 */
	private class itemNameChangedListener implements JVPropertyChangedListener {

		private JVirtualList list;

		@Override
		public void handleEvent(JVPropertyChangedEvent event) throws JVException {
			// 检查传递参数是否正确
			if (!(event.getSource() instanceof JVPropertyString)) {
				throw new JVException("名字属性不是字符串属性！", null);
			}

			// 当项目名称变化时，需要检查项目名称是否与当前容器中的项目重复
			String newName = event.getNewValue().toString();
			if (list.checkItemName(newName)) {
				// 如果重复，则抛出异常阻止名称修改
				throw new JVException("项目名字<" + newName + ">存在重复！", null);
			}
		}

		/**
		 * @param list
		 */
		public itemNameChangedListener(JVirtualList list) {
			super();
			this.list = list;
		}
	}
	
	private itemNameChangedListener itemNameChgListeer;

	/**
	 * 集合下项目列表
	 */
	private HashSet<JVListItem> items;

	public Iterator<JVListItem> getItemsIterator() {
		Iterator<JVListItem> result = items.iterator();
		return result;
	}

	/**
	 * 根据Item的名称返回一个Item对象
	 * 
	 * @param itemName
	 * @return
	 * @throws JVException
	 */
	public JVListItem getItem(String itemName) throws JVException {
		// 缺省不存在
		JVListItem result = null;

		// 遍历每个组件检查
		Iterator<JVListItem> iterator = getItemsIterator();
		JVListItem tmp;
		String str;
		while (iterator.hasNext()) {
			tmp = iterator.next();
			// 取名称，如果相同则返回true
			str = tmp.getName().getValue().toString();
			if (str.equals(itemName)) {
				result = tmp;
				break;
			}
		}
		return result;
	}

	public JVirtualList(JVContainer container) throws JVException {
		super(container);

		items = new HashSet<JVListItem>();
		
		itemNameChgListeer = new itemNameChangedListener(this); 
	}

	/**
	 * 利用反射机制除了，由子类继承返回类
	 * 
	 * @return
	 */
	public Class<?> getItemClass() {
		return JVListItem.class;
	}

	/**
	 * 检查Item的名字在容器中是否唯一
	 * 
	 * @param itemName
	 * @return
	 * 
	 * 		true 存在相同名称的Item
	 * @throws JVException
	 * 
	 * 
	 */
	private Boolean checkItemName(String itemName) throws JVException {
		// 缺省不存在
		Boolean result = false;

		// 遍历每个组件检查
		Iterator<JVListItem> iterator = getItemsIterator();
		JVListItem tmp;
		String str;
		while (iterator.hasNext()) {
			tmp = iterator.next();
			// 取名称，如果相同则返回true
			str = tmp.getName().getValue().toString();
			if (str.equals(itemName)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 自动生成一个项目的名称
	 * 
	 * @return
	 * @throws JVException
	 */
	private String getItemName() throws JVException {
		String result;
		String classname = JVPublicUtility.getClassName(getItemClass().getName());

		// 编号从1开始
		Integer i = 1;
		// 循环，直到得到名字为止
		while (true) {
			// 用组件类名+序号做缺省名称
			result = classname + i.toString();

			// 检查名称是否存在，如果不存在表示名称唯一可以命名，否则编号加1再查
			if (checkItemName(result)) {
				// 名称存在编号加1
				i++;
			} else
				// 没有找到则返回
				break;
		}

		return result;
	}

	/**
	 * 
	 * 通过反射机制创建一个项目对象并插入到项目集合列表中
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws JVException
	 */
	public JVListItem createListItem() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, JVException {
		// 通过子类继承方法得到类
		Class<?> itemClass = getItemClass();
		// 得到类构建方法
		Constructor<?> classConstructor = itemClass.getConstructor(String.class, JVirtualList.class);
		// 通过构建方法创建实例
		JVListItem result = (JVListItem) classConstructor.newInstance(getItemName(), this);

		//防止名称重复的侦听处理类
		result.getName().addListener(itemNameChgListeer);
		
		//加入集合
		items.add(result);
		
		return result;
	}
}
