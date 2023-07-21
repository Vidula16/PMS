package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.ThemeAssetResponseDto;
import com.demo.model.ThemeAsset;
import com.demo.repository.ThemeAssetRepo;

@Service
public class ThemeAssetService {
@Autowired
ThemeAssetRepo repo;

public ThemeAsset addThemeAsset(ThemeAsset themeAsset) {
	return repo.save(themeAsset);
}

public List<ThemeAsset> fetchThemeAsset() {
	return repo.findAll();
}

public List<ThemeAsset> findByAsset(String themeName) {
	return repo.findByAsset(themeName);
}
}
