package com.JVComponents.core;

public abstract class JVConfigFile extends JVDataModule {
	
	/**
	 * 配置文件名
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
	 * 对文件的读写
	 * 
	 * @throws JVException
	 */
	public abstract void readFromFile() throws JVException;
	public abstract void writeToFile() throws JVException;
}

