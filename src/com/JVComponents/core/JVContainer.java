package com.JVComponents.core;

import java.util.*;

/**
 * @author DELL
 * 
 *         ������������࣬���������������ӻ������⻯���
 *
 */
public class JVContainer extends JVComponent {

	/**
	 * @author DELL
	 * 
	 *         ˽���࣬����������Ʊ仯����ֹ��������ظ�
	 *
	 */
	private class ComponentNameChangedListener implements JVPropertyChangedListener {

		private JVContainer container;

		@Override
		public void handleEvent(JVPropertyChangedEvent event) throws JVException {
			// ��鴫�ݲ����Ƿ���ȷ
			if (!(event.getSource() instanceof JVPropertyString)) {
				throw new JVException("�������Բ����ַ������ԣ�", null);
			}

			// ��������Ʊ仯ʱ����Ҫ�����������Ƿ��뵱ǰ�����е�����ظ�
			String newName = event.getNewValue().toString();
			if (container.checkComponentName(newName)) {
				// ����ظ������׳��쳣��ֹ�����޸�
				throw new JVException("�ؼ�����<" + newName + ">�����ظ���", null);
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
	 * ���ɵ�ȫ�����
	 * 
	 */
	private HashSet<JVEmbedComponent> components;

	/**
	 * @return
	 * 
	 * 		�������������
	 */
	public Iterator<JVEmbedComponent> getComponentsIterator() {
		return components.iterator();
	}

	/**
	 * ���������������������Ƿ�Ψһ
	 * 
	 * @param component
	 * @return
	 * 
	 * 		true ������ͬ���Ƶ����
	 * 
	 * @throws JVException
	 * 
	 */
	private Boolean checkComponentName(String name) throws JVException {
		// ȱʡ������
		Boolean result = false;

		// ����ÿ��������
		Iterator<JVEmbedComponent> iterator = getComponentsIterator();
		JVEmbedComponent tmp;
		String str;
		while (iterator.hasNext()) {
			tmp = iterator.next();

			// ȡ���ƣ������ͬ�򷵻�true
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
	 * ��ȡ���ֱ�����ƣ�������ǰ��İ�����
	 * 
	 * @param className
	 * @return
	 */
	private String getClassName(String className) {
		// �������
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
	 * ���������ȡ�������е�Ψһ����
	 * 
	 * @param component
	 * @return
	 * @throws JVException
	 */
	private String getComponentName(JVEmbedComponent component) throws JVException {
		String result;

		// �������
		String classname = getClassName(component.getClass().getName());
		// ��Ŵ�1��ʼ
		Integer i = 1;
		// ѭ����ֱ���õ�����Ϊֹ
		while (true) {
			// ���������+�����ȱʡ����
			result = classname + i.toString();

			// ��������Ƿ���ڣ���������ڱ�ʾ����Ψһ���������������ż�1�ٲ�
			if (checkComponentName(result)) {
				// ���ƴ��ڱ�ż�1
				i++;
			} else
				// û���ҵ��򷵻�
				break;
		}
		return result;
	}

	/**
	 * ����ָ�����������
	 * 
	 * @param component
	 * @param newName
	 * @throws JVException
	 */
	private void setComponentName(JVEmbedComponent component, String newName) throws JVException {
		component.getName().setValue(newName);
	}

	/**
	 * ����һ�����
	 * 
	 * @param component
	 * @throws JVException
	 * 
	 */
	public void addCompnent(JVEmbedComponent component) throws JVException {
		if (!components.contains(component)) {
			// ����������Ʊ仯�¼�������
			component.getName().addListener(cmpNameChangeListeer);

			// ������������ƣ��������ڱ���Ψһ
			String cmpname = component.getName().getValue().toString();

			// ���������ͬ���Ƶ����������Ϊȷʵ���ƣ�����Ҫ��������
			if (checkComponentName(cmpname) | cmpname.equals(JVConsts.componentDefualtName)) {
				// ���»�ȡ����
				cmpname = getComponentName(component);
				// �����������
				setComponentName(component, cmpname);
			}

			// ����������
			components.add(component);
		}
	}

	public void removeComponent(JVEmbedComponent component) {
		components.remove(component);
	}

	public JVContainer(String name) throws JVException {
		super(name);

		// ���ɵ�ȫ�����
		components = new HashSet<JVEmbedComponent>();

		// ������Ʊ仯ʱ����������Ҫ�Ƿ�ֹ��������ظ�
		cmpNameChangeListeer = new ComponentNameChangedListener(this);
	}

}
