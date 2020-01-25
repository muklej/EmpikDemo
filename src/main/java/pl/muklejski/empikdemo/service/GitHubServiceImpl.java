package pl.muklejski.empikdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.muklejski.empikdemo.model.UserDto;
import pl.muklejski.empikdemo.service.constants.GitHubConstants;
import pl.muklejski.empikdemo.service.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class GitHubServiceImpl implements GitHubService {

	private final RestTemplate restTemplate;


	@Override
	public UserDto fetchUser(String login) throws UserNotFoundException {
		try {
			return restTemplate.getForObject(GitHubConstants.USERS_ENDPOINT, UserDto.class, login);
		} catch (HttpClientErrorException error) {
			if (error.getStatusCode().is4xxClientError()) {
				throw new UserNotFoundException(error.getMessage());
			}
			throw error;
		}
	}
}
