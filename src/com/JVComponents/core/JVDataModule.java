package com.JVComponents.core;

/**
 * ����ģ�飬�������ɲ��ɼ����
 * 
 * @author DELL
 *
 */
public class JVDataModule extends JVContainer {

	public JVDataModule(String name) throws JVException {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����һ�����
	 * 
	 * ����ģ������ֻ�����ɲ��ɼ������������Ҫ������֤
	 * 
	 * @param component
	 * @throws JVException 
	 * 
	 */
	public void addCompnent(JVEmbedComponent component) throws JVException {
		//��֤���
		if(component instanceof JVirtualComponent) {
			//�̳и���		
			super.addCompnent(component);
		}else {
			throw new JVException("�����⻯����������಻�ܼ�������ģ�顣", null);
		}
	}
}
