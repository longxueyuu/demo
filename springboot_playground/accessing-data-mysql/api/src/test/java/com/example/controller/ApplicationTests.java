package com.example.controller;

import com.example.domain.model.Account;
import com.example.jpa.User;
import com.example.exception.BizException;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		var users = userService.findAll();
		var s = toJsonStr(users);

		System.out.println(s);
	}

	@Test
	void saveUser() {
		var user = new User();
		user.setName("commit_on_exception");
		user.setEmail("test@spring");
		try {
			userService.save(user);
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testJdbcTemplateQuery() {
		userService.FindAccount();
	}

	@Test
	void testJdbcTemplateTx() {
		Account a = new Account();
		a.setUid("jdbc_test_roll");
		a.setBalance(1000);

		User u = new User();
		u.setEmail("jdbc_test_roll@spring");
		u.setName("jdbc_test_roll");

		try {
			userService.SaveUserAccount(a, u);
			System.out.println("SaveUserAccount: saved");
		} catch (BizException e) {
			e.printStackTrace();
		}
	}



	private String toJsonStr(Object obj) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(obj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}


