package com.daviddev16;

import com.daviddev16.general.model.Role;
import com.daviddev16.general.model.User;
import com.daviddev16.general.repository.RoleRepository;
import com.daviddev16.general.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashSet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService, RoleRepository roleRepository) {
		return (args) ->
		{
			Role costumerRole = roleRepository.customerRole();
			Faker faker = new Faker();
			for (int i = 0; i < 20; i++) {
				User user = User.builder()
						.roles(new HashSet<>())
						.email(faker.bothify("????##@gmail.com"))
						.createdAt(LocalDateTime.now())
						.password(faker.crypto()
								.md5())
						.firstName(faker.name()
								.firstName())
						.lastName(faker.name()
								.lastName())
						.enabled(true)
						.build();

				user.getRoles().add(costumerRole);
				userService.createUser(user);
			}
		};
	}

}
