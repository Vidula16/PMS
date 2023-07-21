package com.demo.model;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.demo.enums.ThemeName;

@Entity
public class ThemeAsset {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int themeId;
private double allocation;
private double themeAllocation;
@ManyToOne
private Theme theme;
@ManyToOne
private Asset asset;

public double getThemeAllocation() {
	return themeAllocation;
}
public void setThemeAllocation(double themeAllocation) {
	this.themeAllocation = themeAllocation;
}
public double getAllocation() {
	return allocation;
}
public void setAllocation(double allocation) {
	this.allocation = allocation;
}
public Theme getTheme() {
	return theme;
}
public void setTheme(Theme theme) {
	this.theme = theme;
}
public Asset getAsset() {
	return asset;
}
public void setAsset(Asset asset) {
	this.asset = asset;
}
public int getThemeId() {
	return themeId;
}
public void setThemeId(int themeId) {
	this.themeId = themeId;
}

}