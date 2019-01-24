package com.JVComponents.core.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.JVComponents.core.JVConfigXMLFile;
import com.JVComponents.core.JVException;

class JVConfigXMLFileTest {
	private static JVConfigXMLFile pluginfile;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pluginfile = new JVConfigXMLFile("plugin", "/home/bob/eclipse-workspace/pluginDemo1/plugin.xml");
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
//		try {
//		pluginfile.writeToFile();
//	} catch (JVException e) {
//		// TODO Auto-generated catch block
//		fail(e.getMessage());
//	}
	}

}
