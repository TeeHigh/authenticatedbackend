package com.unknown.authenticatedbackend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.unknown.authenticatedbackend.models.ApplicationUser;
import com.unknown.authenticatedbackend.models.Role;
import com.unknown.authenticatedbackend.repository.RoleRepository;
import com.unknown.authenticatedbackend.repository.UserRepository;

@SpringBootApplication
public class AuthenticatedbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticatedbackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN")); 
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
