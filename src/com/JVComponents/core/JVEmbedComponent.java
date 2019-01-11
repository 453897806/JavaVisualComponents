package com.JVComponents.core;

public class JVEmbedComponent extends JVComponent {

	/**
	 * ���ӻ�������ڵ�����
	 * 
	 */
	private JVContainer container;
	
	public JVContainer getContainer() {
		return container;
	}

	/**
	 * ������������Ҫָ������
	 * 
	 * ȱʡ�������������������
	 */
	
	public JVEmbedComponent(JVContainer container) throws JVException {
		//��������������������
		super(JVConsts.componentDefualtName);
		
		//����
		this.container = container; 

		//�����������������
		container.addCompnent(this);

	}

}
