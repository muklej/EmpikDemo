package pl.muklejski.empikdemo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.muklejski.empikdemo.TestData;

public class UserTest extends TestData {

	@Test
	public void creationFormUserDtoTest() {
		//given
		UserDto userDto = getValidUserDto();

		//when
		User user = new User(userDto);

		//then
		Assertions.assertEquals(getValidUser(), user);
	}

	@Test
	public void creationFormUserDtoZeroCalculationsTest() {
		//given
		UserDto userDto = getValidUserDtoWithZeroFollowers();

		//when
		User user = new User(userDto);

		//then
		Assertions.assertEquals(getValidUserZeroCalculations(), user);
	}


}