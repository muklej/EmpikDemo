package pl.muklejski.empikdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.muklejski.empikdemo.manager.UserManager;
import pl.muklejski.empikdemo.model.User;
import pl.muklejski.empikdemo.service.exception.UserNotFoundException;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserManager userManager;


	@SuppressWarnings("MVCPathVariableInspection")
	@GetMapping(value = ApiConstants.USER_CONTROLLER_GET_ENDPOINT)
	public User getUser(@PathVariable String login) throws UserNotFoundException {
		return userManager.fetchUserAndIncrementApiCounter(login);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
