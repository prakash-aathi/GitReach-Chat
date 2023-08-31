package com.chat;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.config.DataStaxAstraProperties;

@SpringBootApplication
@RestController
public class GitChatConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitChatConnectApplication.class, args);
	}

	@GetMapping("/user")
	public Object user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal;
	}

	@GetMapping("/")
	public String hey() {
		return "home";
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}
}
