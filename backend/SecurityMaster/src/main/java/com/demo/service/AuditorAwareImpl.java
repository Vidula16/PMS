package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	@Value("${spring.datasource.username}")
	private String userName;
	
@Override
public Optional<String>getCurrentAuditor(){
	return Optional.of(System.getProperty("user.name"));
	//return Optional.of(userName);
}
}
