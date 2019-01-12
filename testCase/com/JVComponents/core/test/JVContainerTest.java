package com.JVComponents.core.test;

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
				fail("新增组件的名称不对");
			}
			//组件更名
			component.getName().setValue("comp1");
			
			//增加组件
			component = new JVEmbedComponent(container);
			component = new JVEmbedComponent(container);
			
			//组件同名触发异常
			component.getName().setValue("comp1");
			
			//未触发异常，报错
			fail("组件同名未报错");
			
		}catch(JVException e) {
			//有异常就正确了
			
		}
		
	}

}
