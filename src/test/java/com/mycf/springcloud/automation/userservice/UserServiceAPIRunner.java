package com.mycf.springcloud.automation.userservice;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceAPIRunner {
	@Autowired
	protected RestTemplate restTemplate;

	private @Value("${external.userservice}") String serviceUrl;

	protected Logger logger = Logger.getLogger(UserServiceAPIRunner.class.getName());

	public User searchByUserId(int userId) {
		System.out.println(serviceUrl);
		return restTemplate.getForObject(serviceUrl + "/" + userId, User.class);
	}
}
