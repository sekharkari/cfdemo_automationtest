package com.mycf.springcloud.automation.userservice;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.mycf.springcloud.automation.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class UserServiceTestRunner extends AbstractTestNGSpringContextTests {

	@Autowired
	UserServiceAPIRunner apiRunner;

	@Test(dataProvider = "Data-Provider-Function")
	public void call(UserServiceTestData testData) {
		User actualResponse = apiRunner.searchByUserId(testData.getUserId());
		assertEquals(testData.getResponse(), actualResponse);
	}

	// This function will provide the patameter data
	@DataProvider(name = "Data-Provider-Function")
	public UserServiceTestData[][] parameterIntTestProvider() throws Exception {
		List<UserServiceTestData> testcases = new ArrayList<UserServiceTestData>();
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("userservice").getFile());
		for (File yamlFile : file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith("yaml");
			}
		})) {
			final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson
																				// databind
			testcases.add(mapper.readValue(yamlFile, UserServiceTestData.class));
		}
		UserServiceTestData[][] output = new UserServiceTestData[testcases.size()][1];
		int i = 0;
		for (UserServiceTestData input : testcases) {
			output[i++][0] = input;
		}
		return output;
	}
}
