package com.JVComponents.Plugin.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;
import com.JVComponents.Plugin.*;

class JVPluginXMLFileTest {
	
	private static JVConfigXMLFile pluginfile;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pluginfile = new JVConfigXMLFile(null, "C:\\develop\\plugin\\plugdemo1\\plugin.xml");
	}

	@Test
	void testReadFromFile() {
		try {
			pluginfile.readFromFile();
		} catch (JVException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
	}

	@Test
	void testWriteToFile() {
		try {
			pluginfile.writeToFile();
		} catch (JVException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}

}
