package com.JVComponents.core;

/**
 * @author DELL
 * 
 * �Զ����쳣
 *
 */
public class JVException extends Exception{

	/**
	 * ���л��汾��
	 */
	private static final long serialVersionUID = JVConsts.serialVersionUID;
	
	/**
	 * @return the originException
	 * 
	 * ԭʼ�쳣
	 * 
	 */
	private Exception OriginException;
	public Exception getOriginException() {
		return OriginException;
	}


	public JVException(String message, Exception e) {
		super(message);
		
		OriginException = e;
	}
	
	@Override
	public String getMessage() {
		String result = super.getMessage();
		if(OriginException != null){
			result = result+ " ������Ϣ��" + OriginException.getMessage();
		}
		return  result; 
	}
}
