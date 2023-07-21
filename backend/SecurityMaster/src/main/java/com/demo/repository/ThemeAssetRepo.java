package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.ThemeAsset;

@Repository
public interface ThemeAssetRepo extends JpaRepository <ThemeAsset,Integer>{
    @Query("select t from ThemeAsset t where t.theme.themeName=?1")
	Double getTotalAllocationPercentageByThemeName(String themeName);
   @Query("select t FROM ThemeAsset t WHERE t.theme. themeName=?1")
	Optional<ThemeAsset> findById(String themeName);
   @Query("select t from ThemeAsset t where t.theme.themeName=?1")
     List<ThemeAsset> findByAsset(String themeName);


}
