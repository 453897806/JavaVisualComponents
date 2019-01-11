package com.JVComponents.core;

/**
 * @author JVC
 *
 *	����java���ӻ�����Ļ���
 */
public class JVComponent {
	
	/**
	 * �������
	 * ��������Ӧ������ı���������ͬ
	 */
	private JVPropertyString name;
	public JVPropertyString getName() {
		return name;
	}
	
	/**
	 * ���캯��
	 * @throws JVException 
	 */
	public JVComponent(String name) throws JVException {
		super();
		
		//��������
		this.name = new JVPropertyString(this, name);		
	}
	
	/**
	 * @return
	 * 
	 * ���ڷ�װ�Ķ���
	 * 
	 * ͨ��������������̳к������ഴ���õ�
	 * 
	 */
	protected Object getPackagedObject(){
		return null;
	}
}
