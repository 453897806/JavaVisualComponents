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
				fail("组件名称不对");
			}
			//设置名称
			component.getName().setValue("comp1");
			
			//第二个
			component = new JVEmbedComponent(container);
			component = new JVEmbedComponent(container);
			
			component.getName().setValue("comp1");
			
			//如果没有触发异常就表示错误没有检查出来
			fail("没有触发异常");
			
		}catch(JVException e) {
			//触发异常是正确的
			
		}
		
	}

}
