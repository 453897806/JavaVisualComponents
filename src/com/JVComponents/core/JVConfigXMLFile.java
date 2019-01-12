package com.JVComponents.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.*;
import org.dom4j.io.*;

public class JVConfigXMLFile extends JVConfigFile {

	/**
	 * XML文件对应的Document对象
	 */
	private Document document;
	public Document getDocument() {
		return document;
	}
	
	/**
	 * @return
	 * 
	 * 用于封装的对象
	 * 
	 */
	protected Object getPackagedObject(){
		return document;
	}
	
	/**
	 * 
	 * 根节点对象
	 * 
	 */
	private JVConfigXMLElement root;
	public JVConfigXMLElement getRoot() {
		return root;
	}
	
	/**
	 * 
	 * 返回根节点名称，子类可以继承并指定子节点名称
	 * 
	 * @return
	 */
	public String getRootName() {
		return "root";
	}
	
	/**
	 * 创建根节点函数，子类可以继承返回不同类型的节点对象
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLElement createRootElement(Element element) throws JVException {
		//如果节点为空则创建
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
			
			//根节点对象
			this.root = createRootElement(this.document.getRootElement());
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
