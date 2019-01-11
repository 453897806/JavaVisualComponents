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
		// 读取
		File file = new File((String) getFileName().getValue());
		try {
			// 文件存在则开启xml文件
			if (file.exists()) {
				SAXReader reader = new SAXReader();
				this.document = reader.read(file);
			} else {// 文件不存在则创建
				this.document = DocumentHelper.createDocument();
			}
		} catch (DocumentException e) {
			throw new JVException("XML文件<" + (String)getFileName().getValue() + ">读取失败！", e);
		}
	}

	@Override
	public void writeToFile() throws JVException {
		try {
			//文件写入
			FileWriter fileWriter = new FileWriter((String)getFileName().getValue());
			document.write(fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			throw new JVException("XML文件<" + (String)getFileName().getValue() + ">写入失败！" , e);
		}


	}

}
