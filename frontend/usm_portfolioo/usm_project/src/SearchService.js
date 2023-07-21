import axios from "axios";

const Search_URL = "http://localhost:1244/master";



class SearchService {


    SearchByISIN(IsinId) {
        return axios.get(`${Search_URL}/fetchDataByIsinNumber/` + IsinId);
    }
    SearchBySymbol(Symbol) {
        return axios.get(`${Search_URL}/fetchDataBySymbol/` + Symbol);
    }
    SearchByDescription(description) {
        return axios.get(`${Search_URL}/fetchDataByDescription/` + description);
    }
    SearchBySector(Sector) {
        return axios.get(`${Search_URL}/fetchDataBySector/` + Sector);
    }
    SearchByIndustry(Industry) {
        return axios.get(`${Search_URL}/fetchDataByIndustry/` + Industry);
    }
    SearchByCurrency(Currency) {
        return axios.get(`${Search_URL}/fetchDataByCurrency/` + Currency);
    }
    SearchByExchange(Exchange) {
        return axios.get(`${Search_URL}/fetchDataByExchange/` + Exchange);
    }
    UpdateData(Symbol, stockData) {
        return axios.put(`${Search_URL}/updateMaster/` + Symbol, stockData);
    }



}



export default new SearchService();