package com.JVComponents.Plugin;

import java.util.*;

import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

public class JVPluginXMLFile extends JVConfigXMLFile {

	/**
	 * 
	 * ���ظ��ڵ�����
	 *  Plugin.xml��һ�����ڵ㣬��Ϊ'plugin'
	 * 
	 * @return
	 */
	@Override
	public String getRootName() {
		return JVPluginConsts.rootName;
	}

	public JVPluginXMLFile(String name, String filename) throws JVException {
		super(name, filename);
	}
	
	/**
	 * ���ݽڵ���󴴽���չ����
	 * 
	 * @return
	 * @throws JVException 
	 */
	private JVPluginExtension createExtension(JVConfigXMLElement element) throws JVException {
		JVPluginExtension result = JVPluginExtensionFactory.createPluginExtension(this, element);
		//������չ��
		addCompnent(result);
		return result;
	}
		
	@Override
	public void readFromFile() throws JVException {
		super.readFromFile();
	
		// �����ڵ��Ƿ���ȷ
		String str =(String)getRoot().getName().getValue(); 
		if (!str.equals(getRootName())) {
			throw new JVException("���ڵ㲻��<" + getRootName() + ">", null);
		}
		
		//����ÿһ����չ��
		JVConfigXMLElement root = getRoot();
		Iterator<JVConfigXMLElement> iter = root.getElementsIterator();
		while(iter.hasNext()) {
			createExtension(iter.next());
		}
	}
	
	

}
