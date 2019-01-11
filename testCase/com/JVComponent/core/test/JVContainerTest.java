package com.JVComponent.core.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVEmbedComponent;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVConsts;

class JVContainerTest {
	
	private static JVContainer container;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		container = new JVContainer("container"); 
	}

	@Test
	void testAddCompnent() {
		try {
			JVEmbedComponent component = new JVEmbedComponent(container);
			
			if (component.getName().getValue().toString().equals(JVConsts.componentDefualtName)) {
				fail("������Ʋ���");
			}
			//��������
			component.getName().setValue("comp1");
			
			//�ڶ���
			component = new JVEmbedComponent(container);
			component = new JVEmbedComponent(container);
			
			component.getName().setValue("comp1");
			
			//���û�д����쳣�ͱ�ʾ����û�м�����
			fail("û�д����쳣");
			
		}catch(JVException e) {
			//�����쳣����ȷ��
			
		}
		
	}

}
