import React from 'react'
import axios from 'axios'
const FETCH_URL="http://localhost:1244/portfolio/fetchPortfolio"
const BASE_URL="http://localhost:1244/portfolio/addPortfolio"
const GET_URL="http://localhost:1244/theme/fetchTheme"

class PortfolioHeaderService{
    
    getAll(){
        return axios.get(FETCH_URL);
    }
    addPortfolio(portfolioHeader)
    {   
        return axios.post(BASE_URL,portfolioHeader);
       
    }
    fetchThemeAsset(){
        return axios.get(GET_URL);
    }
}
export default new PortfolioHeaderService;