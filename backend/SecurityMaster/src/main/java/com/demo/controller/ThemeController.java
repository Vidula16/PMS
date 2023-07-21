package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Theme;
import com.demo.model.ThemeAsset;
import com.demo.service.ThemeService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/theme")
public class ThemeController {
	@Autowired
	ThemeService service;
	@PostMapping("/addTheme")
	public ResponseEntity<Theme>addTheme(@RequestBody Theme theme){
		Theme object=service.addTheme(theme);
		return new ResponseEntity<>(object,HttpStatus.OK);
}
	@GetMapping("/fetchTheme")
    public ResponseEntity<List<Theme>>fetchTheme(){
	List<Theme> theme=service.fetchTheme();
	return new ResponseEntity<>(theme,HttpStatus.OK);
}
}
