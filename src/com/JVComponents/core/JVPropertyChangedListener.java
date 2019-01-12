package com.JVComponents.core;

/**
 * @author DELL
 *
 *	属性变化侦听接口
 */

import java.util.EventListener;

public interface JVPropertyChangedListener extends EventListener{
	//事件处理句柄
	public void handleEvent(JVPropertyChangedEvent event) throws JVException;
}
