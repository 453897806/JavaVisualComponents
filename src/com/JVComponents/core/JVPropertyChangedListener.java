package com.JVComponents.core;

/**
 * @author DELL
 *
 *	���Ա仯�����ӿ�
 */

import java.util.EventListener;

public interface JVPropertyChangedListener extends EventListener{
	//�¼�������
	public void handleEvent(JVPropertyChangedEvent event) throws JVException;
}
