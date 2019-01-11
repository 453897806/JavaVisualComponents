package com.JVComponents.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.*;
import org.dom4j.io.*;

public class JVConfigXMLFile extends JVConfigFile {

	private Document document;
	public Document getDocument() {
		return document;
	}

	public JVConfigXMLFile(JVContainer container, String filename) throws JVException {
		super(container, filename);
		
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
