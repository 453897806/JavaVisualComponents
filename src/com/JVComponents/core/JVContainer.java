package com.JVComponents.core;

import java.util.*;

/**
 * @author DELL
 * 
 * ������������࣬���������������ӻ������⻯���
 *
 */
public class JVContainer extends JVComponent {
	
	/**
	 * @author DELL
	 * 
	 * ˽���࣬����������Ʊ仯����ֹ��������ظ�
	 *
	 */
	private class ComponentNameChangedListener implements JVPropertyChangedListener {
		
		private JVContainer container;

		@Override
		public void handleEvent(JVPropertyChangedEvent event) throws JVException {
			//��鴫�ݲ����Ƿ���ȷ
			if(! (event.getSource() instanceof JVPropertyString)) {
				throw new JVException ("�������Բ����ַ������ԣ�",null);
			}
			
			//��������Ʊ仯ʱ����Ҫ�����������Ƿ��뵱ǰ�����е�����ظ�
			String newName = event.getNewValue().toString();
			if(container.checkComponentName(newName)) {
				//����ظ������׳��쳣��ֹ�����޸�
				throw new JVException ("�ؼ�����<"+ newName + ">�����ظ���",null);
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
	 * �������������
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
	 *   true ������ͬ���Ƶ����
	 * 
	 * @throws JVException 
	 * 
	 */
	private Boolean checkComponentName(String name) throws JVException {
		//ȱʡ������
		Boolean result = false;
		
		//����ÿ��������
		Iterator<JVEmbedComponent> iterator = getComponentsIterator();
		JVEmbedComponent tmp ;
		String str;
		while(iterator.hasNext()) {
			tmp = iterator.next();
		
			//ȡ���ƣ������ͬ�򷵻�true
			str = tmp.getName().getValue().toString();
			if(str.equals(name)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	private String getComponentName(JVEmbedComponent component) throws JVException{
		//��Ŵ�1��ʼ
		Integer i = 1;
		
		String result ;  
		//ѭ����ֱ���õ�����Ϊֹ
		while(true) {
			//���������+�����ȱʡ����
			result = component.getClass().getName() + i.toString();
			
			//��������Ƿ���ڣ���������ڱ�ʾ����Ψһ���������������ż�1�ٲ�
			if(checkComponentName(result)) {
				//���ƴ��ڱ�ż�1				
				i++;
			}else 
			  //û���ҵ��򷵻�
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
		if(!components.contains(component)) {
			//������������ƣ��������ڱ���Ψһ
			String cmpname = component.getName().getValue().toString();
			
			//����������Ʊ仯�¼�������
			component.getName().addListener(cmpNameChangeListeer);
			
			//���������ͬ���Ƶ����������Ϊȷʵ���ƣ�����Ҫ��������
			if(checkComponentName(cmpname) | 
			   cmpname.equals(JVConsts.componentDefualtName)) {
				//���»�ȡ����
				cmpname = getComponentName(component);
				//�����������
				setComponentName(component, cmpname);
			}
			
			//����������
			components.add(component);
		}
	}
	
	public void removeComponent(JVEmbedComponent component) {
		components.remove(component);
	}

	public JVContainer(String name) throws JVException {
		super(name);

		//���ɵ�ȫ�����
		components = new HashSet<JVEmbedComponent>(); 
		
		//������Ʊ仯ʱ����������Ҫ�Ƿ�ֹ��������ظ�		
		cmpNameChangeListeer = new ComponentNameChangedListener(this);		
	}

}
