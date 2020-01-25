package pl.muklejski.empikdemo.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.muklejski.empikdemo.model.User;
import pl.muklejski.empikdemo.model.UserDto;
import pl.muklejski.empikdemo.repository.ApiCounterRepository;
import pl.muklejski.empikdemo.service.GitHubService;
import pl.muklejski.empikdemo.service.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class UserManager {

	private final GitHubService gitHubService;
	private final ApiCounterRepository apiCounterRepository;


	public User fetchUserAndIncrementApiCounter(String login) throws UserNotFoundException {
		apiCounterRepository.incrementApiCounterOrCreate(login);
		UserDto userDTO = gitHubService.fetchUser(login);
		return new User(userDTO);
	}
}
