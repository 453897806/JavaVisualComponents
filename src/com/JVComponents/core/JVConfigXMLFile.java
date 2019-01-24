package com.JVComponents.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.*;
import org.dom4j.io.*;

public class JVConfigXMLFile extends JVConfigFile {

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
	
	public JVConfigXMLFile(String name, String filename) throws JVException {
		super(name, filename);	
	}
	
	/**
	 * 根据节点创建节点对象，子类继承可创建不同节点对象
	 * 
	 * @param element
	 * @return
	 * @throws JVException
	 */
	public JVConfigXMLElement createXMLElement(Element element) throws JVException{
		return new JVConfigXMLElement(this, element);
	}
	
	@Override
	public void readFromFile() throws JVException {
		Document document;
		// 读取
		File file = new File((String) getFileName().getValue());
		try {
			// 文件存在则开启xml文件
			if (file.exists()) {
				SAXReader reader = new SAXReader();
				document = reader.read(file);
			} else {// 文件不存在则创建
				document = DocumentHelper.createDocument();
			}
			
			//根节点对象
			Element element = document.getRootElement();
			//如果节点为空则创建
			if(element == null) {
				element = document.addElement(getRootName());
			}else {
				// 检查根节点是否正确
				String str = element.getName(); 
				if (!str.equals(getRootName())) {
					throw new JVException("根节点不是<" + getRootName() + ">", null);
				}
			}
			//创建根节点对象，并自动读取属性及子节点
			this.root = createXMLElement(element);
			
		} catch (DocumentException e) {
			throw new JVException("XML文件<" + (String)getFileName().getValue() + ">读取失败！", e);
		}
	}

	@Override
	public void writeToFile() throws JVException {
		try {
			//新建xml文档并写入
			Document document = DocumentHelper.createDocument();
			//构建根结点
			Element rootElement = document.addElement(getRootName());
			//根节点写入
			this.root.writeToDocument(rootElement, null);
			//文件写入
			FileWriter fileWriter = new FileWriter((String)getFileName().getValue());
			document.write(fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			throw new JVException("XML文件<" + (String)getFileName().getValue() + ">写入失败！" , e);
		}


	}

}
