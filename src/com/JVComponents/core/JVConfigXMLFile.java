package com.JVComponents.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.*;
import org.dom4j.io.*;

public class JVConfigXMLFile extends JVConfigFile {

	/**
	 * XML�ļ���Ӧ��Document����
	 */
	private Document document;
	public Document getDocument() {
		return document;
	}
	
	/**
	 * @return
	 * 
	 * ���ڷ�װ�Ķ���
	 * 
	 */
	protected Object getPackagedObject(){
		return document;
	}
	
	/**
	 * 
	 * ���ڵ����
	 * 
	 */
	private JVConfigXMLElement root;
	public JVConfigXMLElement getRoot() {
		return root;
	}
	
	/**
	 * 
	 * ���ظ��ڵ����ƣ�������Լ̳в�ָ���ӽڵ�����
	 * 
	 * @return
	 */
	public String getRootName() {
		return "root";
	}
	
	/**
	 * �������ڵ㺯����������Լ̳з��ز�ͬ���͵Ľڵ����
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLElement createRootElement(Element element) throws JVException {
		//����ڵ�Ϊ���򴴽�
		if(element == null) {
			element = document.addElement(getRootName());
		}
		
		return new JVConfigXMLElement(this, element);
	}

	public JVConfigXMLFile(String name, String filename) throws JVException {
		super(name, filename);	
	}

	@Override
	public void readFromFile() throws JVException {
		// ��ȡ
		File file = new File((String) getFileName().getValue());
		try {
			// �ļ���������xml�ļ�
			if (file.exists()) {
				SAXReader reader = new SAXReader();
				this.document = reader.read(file);
			} else {// �ļ��������򴴽�
				this.document = DocumentHelper.createDocument();
			}
			
			//���ڵ����
			this.root = createRootElement(this.document.getRootElement());
		} catch (DocumentException e) {
			throw new JVException("XML�ļ�<" + (String)getFileName().getValue() + ">��ȡʧ�ܣ�", e);
		}
	}

	@Override
	public void writeToFile() throws JVException {
		try {
			//�ļ�д��
			FileWriter fileWriter = new FileWriter((String)getFileName().getValue());
			document.write(fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			throw new JVException("XML�ļ�<" + (String)getFileName().getValue() + ">д��ʧ�ܣ�" , e);
		}


	}

}
