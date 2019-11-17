package com.joyzone.platform.model.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@WebAppConfiguration
public class FileTest {
	
	/*@Autowired
	private FileUtil util;*/
	
	@Test
	public void testFile() throws FileNotFoundException {
		//util.uploadPersonalImg(new FileInputStream(new File("d:\\ip.properties")), "ip.properties");
	}

}
