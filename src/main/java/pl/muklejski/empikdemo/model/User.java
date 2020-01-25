package pl.muklejski.empikdemo.model;

import java.time.ZonedDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;


@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

	private long id;
	private String login;
	private String name;
	private String type;
	private String avatarUrl;
	private ZonedDateTime createdAt;
	private long calculations;


	public User(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.createTypeMap(UserDto.class, User.class)
			.addMappings(calcMapping());
		modelMapper.map(userDto, this);
	}

	private PropertyMap<UserDto, User> calcMapping() {
		return new PropertyMap<UserDto, User>() {
			@Override
			protected void configure() {
				using(ctx -> calcCalculations((UserDto) ctx.getSource()))
					.map(source, destination.getCalculations());
			}
		};
	}

	private long calcCalculations(UserDto userDTO) {
		long followers = Optional.ofNullable(userDTO).map(UserDto::getFollowers).orElse(0L);
		if (followers == 0) {
			return 0L;
		} else {
			return (6L / followers * (2L + userDTO.getPublicRepos()));
		}
	}
}
