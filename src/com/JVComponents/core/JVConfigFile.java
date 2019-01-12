package com.JVComponents.core;

public abstract class JVConfigFile extends JVDataModule {
	
	/**
	 * �����ļ���
	 */
	private JVPropertyString fileName;
	public JVPropertyString getFileName() {
		return fileName;
	}


	public JVConfigFile(String name, String filename) throws JVException {
		super(name);
		
		this.fileName = new JVPropertyString(this, filename);
	}

	
	/**
	 * ���ļ��Ķ�д
	 * 
	 * @throws JVException
	 */
	public abstract void readFromFile() throws JVException;
	public abstract void writeToFile() throws JVException;
}

