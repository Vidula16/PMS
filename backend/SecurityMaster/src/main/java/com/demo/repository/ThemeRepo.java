package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Theme;

@Repository
public interface ThemeRepo extends JpaRepository<Theme,String> {

}
