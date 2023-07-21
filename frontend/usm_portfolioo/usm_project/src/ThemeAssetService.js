import React from 'react'
import axios from 'axios'
const FETCH_URL = "http://localhost:1244/themeAsset/fetchThemeAsset"
const BASE_URL = "http://localhost:1244/themeAsset/addThemeAsset"
const GET_URL = "http://localhost:1244/asset/fetchAsset"
const theme_url = "http://localhost:1244/asset/fetchAssetByThemeName"
const fetchTheme = "http://localhost:1244/themeAsset/fetchThemeAsset"
class ThemeAssetService {

    addThemeAsset(themeAsset, assetClass) {
        return axios.post(`${BASE_URL}/` + assetClass, themeAsset);

    }
    fetchThemeAsset(themeName) {
        return axios.get(`${theme_url}/` + themeName);
    }
    getAll() {
        return axios.get(GET_URL);
    }
    fetchTheme() {
        return axios.get(fetchTheme);
    }

}
export default new ThemeAssetService;