package com.JVComponents.Plugin.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.JVComponents.Plugin.*;
import com.JVComponents.core.JVConfigXMLAttribute;
import com.JVComponents.core.JVConfigXMLElement;
import com.JVComponents.core.JVException;

class JVPluginXMLFileTest {
	
	private static JVPluginXMLFile pluginfile;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pluginfile = new JVPluginXMLFile("plugin", "/home/bob/eclipse-workspace/pluginDemo1/plugin.xml");
	}
	
	public String getString(Integer i) {
		StringBuilder result = new StringBuilder("");
		for(int k=0; k<i;k++) {
			result.append("  ");
		}
		return result.toString();
	}
	
	public void printElement(JVConfigXMLElement element, Integer index)throws JVException  {
		StringBuilder str = new StringBuilder(getString(index));
		str.append(element.getName().getValue().toString());
		
		//属性
		Iterator<JVConfigXMLAttribute> iter = element.getXmlAttributes().iterator();
		JVConfigXMLAttribute tmp;
		while(iter.hasNext()) {
			tmp = iter.next();
			str.append(" ")
				.append(tmp.getName().getValue().toString())
				.append("=")
				.append(tmp.getValue().getValue().toString());
		}
		System.out.println(str);
		
		//子节点
		Iterator<JVConfigXMLElement> itere = element.getSubXMLElements().iterator();
		JVConfigXMLElement tmpe;
		while(itere.hasNext()) {
			tmpe = itere.next();
			printElement(tmpe, index+1);
		}
		
	}

	@Test
	void testReadFromFile() {
		try {
			pluginfile.readFromFile();
			
			//打印
			printElement(pluginfile.getRoot(), 0);
			
		} catch (JVException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
	}

	@Test
	void testWriteToFile() {
//		try {
//			pluginfile.writeToFile();
//		} catch (JVException e) {
//			// TODO Auto-generated catch block
//			fail(e.getMessage());
//		}
	}

}
