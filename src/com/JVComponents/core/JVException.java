package com.JVComponents.core;

/**
 * @author DELL
 * 
 * 自定义异常
 *
 */
public class JVException extends Exception{

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = JVConsts.serialVersionUID;
	
	/**
	 * @return the originException
	 * 
	 * 原始异常
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
			result = result+ " 错误信息：" + OriginException.getMessage();
		}
		return  result; 
	}
}
