package pl.muklejski.empikdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import pl.muklejski.empikdemo.TestData;
import pl.muklejski.empikdemo.model.User;
import pl.muklejski.empikdemo.model.UserDto;
import pl.muklejski.empikdemo.service.constants.GitHubConstants;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class UserControllerTest extends TestData {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private RestTemplate restTemplate;


	@BeforeEach
	public void initMock() {
		doReturn(getValidUserDto()).when(restTemplate).getForObject(GitHubConstants.USERS_ENDPOINT, UserDto.class, "muklej");
		doThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND)).when(restTemplate).getForObject(GitHubConstants.USERS_ENDPOINT, UserDto.class, "");

	}


	@Test
	public void givenWac_whenServletContext_thenItProvidesUserController() {
		//given
		ServletContext servletContext = wac.getServletContext();

		//then
		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
		assertNotNull(wac.getBean("userController"));
	}

	@Test
	public void emptyLoginTest() throws Exception {
		//given
		String login = "";

		//when
		mockMvc.perform(get(ApiConstants.USER_CONTROLLER_GET_ENDPOINT, login))
			//then
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void validLoginTest() throws Exception {
		//given
		String login = "muklej";

		//when
		MvcResult mvcResult = mockMvc.perform(get(ApiConstants.USER_CONTROLLER_GET_ENDPOINT, login))
			//then
			.andExpect(status().isOk())
			.andReturn();

		//then
		User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
		assertEquals(getValidUser(), user);
	}
	}