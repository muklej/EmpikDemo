package pl.muklejski.empikdemo.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.muklejski.empikdemo.TestData;
import pl.muklejski.empikdemo.controller.ApiConstants;
import pl.muklejski.empikdemo.model.ApiCounterEntity;
import pl.muklejski.empikdemo.model.UserDto;
import pl.muklejski.empikdemo.service.constants.GitHubConstants;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ApiCounterRepositoryTest extends TestData {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestTemplate restTemplate;

	@Autowired
	private ApiCounterRepository apiCounterRepository;


	@BeforeAll
	public void initMock() {
		when(restTemplate.getForObject(eq(GitHubConstants.USERS_ENDPOINT), eq(UserDto.class), eq("Giorgio")))
			.thenReturn(getValidUserDto());
		when(restTemplate.getForObject(eq(GitHubConstants.USERS_ENDPOINT), eq(UserDto.class), eq("")))
			.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

	}


	@Test
	public void validCountingTest() throws Exception {
		//given
		String login = "Giorgio";

		//when
		mockMvc.perform(get(ApiConstants.USER_CONTROLLER_GET_ENDPOINT, login));
		mockMvc.perform(get(ApiConstants.USER_CONTROLLER_GET_ENDPOINT, login));
		ApiCounterEntity apiCounter = apiCounterRepository.findById("Giorgio").orElse(new ApiCounterEntity());

		//then
		assertEquals(login, apiCounter.getLogin());
		assertEquals(2L, apiCounter.getRequestCount().get());

	}


}