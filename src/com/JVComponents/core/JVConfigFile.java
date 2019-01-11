package com.JVComponents.core;

public abstract class JVConfigFile extends JVirtualComponent {
	
	/**
	 * 配置文件名
	 */
	private JVPropertyString fileName;
	public JVPropertyString getFileName() {
		return fileName;
	}


	public JVConfigFile(JVContainer container, String filename) throws JVException {
		super(container);
		
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

