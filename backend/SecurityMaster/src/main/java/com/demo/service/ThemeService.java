package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Theme;
import com.demo.repository.ThemeRepo;

@Service
public class ThemeService {
@Autowired
ThemeRepo repo;
	public Theme addTheme(Theme theme) {
		return repo.save(theme);
	}
	public List<Theme> fetchTheme() {
		return repo.findAll();
	}

}
