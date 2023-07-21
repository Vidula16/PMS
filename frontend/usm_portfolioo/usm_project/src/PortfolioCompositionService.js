import React from 'react'
import axios from 'axios'
import PortfolioComposition from './PortfolioComposition';
const FETCH_URL = "http://localhost:1213/compo/fetchCompo"
const BASE_URL = "http://localhost:1213/compo/addCompo"
const GET_URL = "http://localhost:1213/themeAsset/fetchThemeAsset"
class PortfolioCompositionService {

    getComposition() {
        return axios.get(FETCH_URL);
    }
    addCompo(portfolioComposition) {
        return axios.post(BASE_URL, portfolioComposition);

    }
    fetchThemeAsset() {
        return axios.get(GET_URL);
    }

}
export default new PortfolioCompositionService;