package com.JVComponents.core;

/**
 * @author DELL
 * 
 * ���п��ӻ��ؼ��Ļ��࣬�������������ڿɼ�
 *
 */
public class JVisualComponent extends JVEmbedComponent {
	


	public JVisualComponent(JVContainer container) throws JVException {
		super(container);
		
		//��������
		visible = new JVPropertyBoolean(this, true);
	}
	
	
	/**
	 *	�Ƿ�ɼ� 
	 */
	private JVPropertyBoolean visible;
	public JVPropertyBoolean getVisible() {
		return visible;
	} 
	

}
