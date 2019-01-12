package com.JVComponents.Plugin;

import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVirtualComponent;

/**
 * 
 * ��װ��plugin�µ���չ
 * 
 * @author DELL
 *
 */
public abstract class JVPluginExtension extends JVirtualComponent {

	/**
	 * ��Ӧ��plugin�ļ�
	 */
	private JVPluginXMLFile pluginFile;

	public JVPluginXMLFile getPluginFile() {
		return pluginFile;
	}

	/**
	 * ��Ӧ�Ľڵ����
	 */
	private JVConfigXMLElement element;

	public JVConfigXMLElement getElement() {
		return element;
	}

	/**
	 * 
	 * ����̳в�������չ������
	 * 
	 * @return
	 */
	public abstract String getExtensionPoint();

	/**
	 * point����
	 */
	private JVConfigXMLAttribute point;

	public JVConfigXMLAttribute getPoint() {
		return point;
	}

	/**
	 * �����Ѿ����ڵĽڵ���д���
	 * 
	 * @param container
	 * @param element
	 * @throws JVException
	 */
	public JVPluginExtension(JVContainer container, JVConfigXMLElement element) throws JVException {
		super(container);
		
		//���
		if(!(container instanceof JVPluginXMLFile)){
			throw new  JVException("����Plugin��XML�ļ���",null);
		}
		//��Ա
		this.pluginFile = (JVPluginXMLFile) container;
		this.element = element;
		
		if(pluginFile != element.getConfigXMLFile()) {
			throw new  JVException("�ڵ���plugin�ļ�����ͬһ������",null);
		}
		
		//point���Զ���
		point = element.findAttribute(JVPluginConsts.attributePoint);
		if(point == null) {
			throw new JVException("��<" + JVPluginConsts.attributePoint +">���ԡ�", null);
		}
		
		//point���Զ��������뵱ǰ��չ��һ��
		String pointValue = (String)point.getValue().getValue();
		if(!pointValue.equals(getExtensionPoint())) {
			throw new JVException("��չ�㲻��<" + getExtensionPoint() +">��", null);
		}
		
		//���ݽڵ㹹�����Ժ��¼��ڵ�
		createSubNode();
	}
	
}
