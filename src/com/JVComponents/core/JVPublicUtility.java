package com.JVComponents.core;

/**
 * 公共函数集合
 * 
 * @author bob
 *
 */
public class JVPublicUtility {

	public JVPublicUtility() {
	}

	/**
	 * 
	 * 获取类的直接名称，不包含前面的包名称
	 * 
	 * @param className
	 * @return
	 */
	public static String getClassName(String className) {
		// 组件类名
		String result = className;
		Integer index = result.lastIndexOf(".");
		if (index >= 0) {
			result = result.substring(index + 1, result.length());
		}

		return result;
	}

}
