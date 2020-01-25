package pl.muklejski.empikdemo.service;

import pl.muklejski.empikdemo.model.UserDto;
import pl.muklejski.empikdemo.service.exception.UserNotFoundException;


public interface GitHubService {

	UserDto fetchUser(String login) throws UserNotFoundException;
}
