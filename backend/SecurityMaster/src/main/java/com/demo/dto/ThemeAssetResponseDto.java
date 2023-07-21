package com.demo.dto;

public class ThemeAssetResponseDto {
		private Double allocation;
		private String themeName;
		private String assetId;
		
		public Double getAllocation() {
			return allocation;
		}
		public void setAllocation(Double allocation) {
			this.allocation = allocation;
		}
		public String getThemeName() {
			return themeName;
		}
		public void setThemeName(String themeName) {
			this.themeName = themeName;
		}
		public String getAssetId() {
			return assetId;
		}
		public void setAssetId(String assetId) {
			this.assetId = assetId;
		}
		
}