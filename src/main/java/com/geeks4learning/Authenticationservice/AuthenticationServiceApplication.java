package com.geeks4learning.Authenticationservice;

import com.geeks4learning.Authenticationservice.Enum.Role;
import com.geeks4learning.Authenticationservice.Model.User;
import com.geeks4learning.Authenticationservice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationServiceApplication implements CommandLineRunner {
    @Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = this.userRepository.findByRole(Role.ADMIN);

		if (adminAccount == null) {

			User user = User.builder()
					.firstname("John")
					.lastname("Smith")
					.email("admin@gmail.com")
					.user_password(new BCryptPasswordEncoder().encode("Admin"))
					.role(Role.ADMIN)
					.build();
			this.userRepository.save(user);
		}
	}

}
