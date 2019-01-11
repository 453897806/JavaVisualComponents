package com.JVComponents.core;

public abstract class JVConfigFile extends JVirtualComponent {
	
	/**
	 * �����ļ���
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
	 * ���ļ��Ķ�д
	 * 
	 * @throws JVException
	 */
	public abstract void readFromFile() throws JVException;
	public abstract void writeToFile() throws JVException;
}

