package com.demo.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.demo.enums.ThemeName;
@Entity
public class Theme {
@Id
private String themeName;

public String getThemeName() {
	return themeName;
}

public void setThemeName(String themeName) {
	this.themeName = themeName;
}

}