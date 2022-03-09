package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	
	@Test
	void testGetAllUsers() throws Exception{
		
		String uri = STARTING_URI + "/users";
		
		List<User> allUsers = Arrays.asList(
				new User(1, "eric", "eric@gmail.com", "eric123", null, true, null),
				new User(1, "john", "john@gmail.com", "john123", null, true, null));
		
		when( userService.getAllUsers() ).thenReturn(allUsers);
		
		mvc.perform(get(uri)) //peform request
		.andDo(print()) //..print the request sent and response returned
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length(").value(allUsers.size())) // expected number of elements
		;
		
		//verify --> check how many interactions with code there are
		verify(userService, times(1)).getAllUsers();
		verifyNoMoreInteractions(userService);
		
	}
	
	@Test
	void testCreateUser() throws Exception{
		String uri = STARTING_URI + "/users";
		User user = new User(1, "eric", "eric@gmail.com", "eric123", null, true, null);
		
		when(userService.createUser(user))
		.thenReturn(user);
		
		mvc.perform( post(uri)
				.contentType( MediaType.APPLICATION_JSON_VALUE )
				.content(asJsonString(user) ));
		
		
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
