import React, { useState, useEffect } from "react";

import Navbar from "./Navbar";

import './PortfolioAdd.css';

import home from './Images/home.png';

// import back from './Images/back.png';
import PortfolioHeaderService from "./PortfolioHeaderService";

import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
const PortfolioAdd = () => {
    const [portfolios, setPortfolios] = useState('')
    const [portfolioName, setPortfolioName] = useState('')
    const [baseCurrency, setBaseCurrency] = useState('')
    const [benchMark, setBenchMark] = useState('')
    const [themeName, setThemeName] = useState([])
    const [fundManagerName, setFundManagerName] = useState('')
    const [rebalancingFrequency, setRebalancingFrequency] = useState('')
    const [investmentValue, setInvestmentValue] = useState('')
    const [urlValue, setUrlValue] = useState('')
    const [exchange,setExchange] = useState('');
    const [error,seterror] = useState('');
    const BASE_URL = "http://localhost:1244/portfolio/addPortfolio"
    const navigate = useNavigate();

    function changeUrlValue(e) {
        setUrlValue(e.target.value);
    }
    function changeRebalancingFrequency(e) {
        setRebalancingFrequency(e.target.value);
    }
    function changeBaseCurrency(e) {
        setBaseCurrency(e.target.value);
    }
    function changeBenchMark(e) {
        setBenchMark(e.target.value);
    }
    function changeExchange(e) {
        setExchange(e.target.value);
    }
    function getTheme() {
        PortfolioHeaderService.fetchThemeAsset().then(
            (response) => { setThemeName(response.data) }
        )
    }
    function addPortfolio(urlValue, portfolioHeader) {
        return axios.post(`${BASE_URL}/` + urlValue, portfolioHeader);

    }
    console.log(urlValue)





    const savePortfolio = (event) => {

        if (portfolioName == '' || baseCurrency == '' || benchMark == '' || themeName == '' || fundManagerName == '' || rebalancingFrequency == '' || investmentValue == '' || exchange == '') {

            window.alert("Please fill all the fields")

        }else {

        //event.preventDefault()

        const portfolioHeader = { portfolioName, baseCurrency, benchMark, themeName, fundManagerName, rebalancingFrequency, investmentValue }

        console.log(portfolioHeader);

        addPortfolio(urlValue, portfolioHeader).then(

            (response) => {

                console.log(response.data);

                // navigate('/securitypage', { state: { data: portfolioName } })

            },
            navigate('/securitypage', { state: {data:portfolioHeader.portfolioName } })
                //navigate(`/securitypage/${portfolioHeader.portfolioName}`)
            ).catch(

                error => { console.log(error); })

    }}



    //     checkfields()

    //     if (error == true) {

    //         window.alert("please fill all the fields");

    //         setExchange("")

    //         seterror(false)

    //         return <PortfolioAdd></PortfolioAdd>

    //     }
    //     event.preventDefault()
    //     const portfolioHeader = { portfolioName, baseCurrency, benchMark, themeName, fundManagerName, rebalancingFrequency, investmentValue }
    //     console.log(portfolioHeader);
    //     addPortfolio(urlValue, portfolioHeader).then(
    //         (response) => {
    //             console.log(response.data);
    //             navigate('/securitypage', { state: { data: portfolioName } });
    //         }).catch(
    //             error => { console.log(error); })
    // }


    useEffect(() => {
        getTheme()
    }, [])

    return (



        <div>
            <div className="containerf">
                <div class="title1">PortfolioAdd</div>
                <form action="#">
                    <div class="portfolio-details">
                        <div class="input-box">
                            <span class="details">Portfolio Name</span>
                            <input type="text" placeholder="Enter the portfolio name" value={portfolioName}
                                onChange={(event) => setPortfolioName(event.target.value)} required></input>
                        </div>
                        <div class="input-box">
                            <span class="details">Base Currency</span>
                            <select className="selections" value={baseCurrency} onChange={changeBaseCurrency}>
                                <option>----Choose Currency----</option>
                                <option value="INR">INR</option>

                                <option value="USD">USD</option>

                                <option value="GBP">GBP</option>

                            </select><br /> <br />

                        </div>
                        <div class="input-box">
                            <span class="details">Fund Manager Name</span>
                            <input type="text" placeholder="Enter Fund Manager name" value={fundManagerName}
                                onChange={(event) => setFundManagerName(event.target.value)} required></input>
                        </div>
                        <div class="input-box">
                            <span class="details">Benchmark</span>
                            <select className="selections" value={benchMark} onChange={changeBenchMark}>
                                <option>---Choose Benchmark---</option>
                                <option value="NIFTY">NIFTY</option>
                                <option value="NIFTY50">NIFTY50</option>
                                <option value="NIFTY100">NIFTY100</option>
                            </select><br /> <br />
                        </div>
                        <div class="input-box">
                            <span class="details">Investment Value</span>
                            <input type="text" placeholder="Enter the Investment Value" value={investmentValue}
                                onChange={(event) => setInvestmentValue(event.target.value)} required></input>
                        </div>
                        <div class="input-box">
                            <span class="details">Rebalancing Frequency</span>
                            <select className="selections" value={rebalancingFrequency} onChange={changeRebalancingFrequency}>
                                <option>-Rebalancing Frequency-</option>
                                <option value="DAILY">DAILY</option>
                                <option value="MONTHLY">MONTHLY</option>
                                <option value="QUATERLY">QUATERLY</option>
                                <option value="YEARLY">YEARLY</option>
                                <option value="SIXMONTHS">SIXMONTHS</option>
                            </select><br /> <br />
                        </div>
                        <div class="input-box" >
                            <span class="details">Theme Name</span>
                            <select className="selections"  value={urlValue} onChange={changeUrlValue}>
                                <option>--------Choose Theme---------</option>
                                {
                                    themeName ? (themeName.map((data, key) => (
                                        <option value={data.themeName}> {data.themeName}</option>
                                    ))) : (<>Loading...</>)
                                }

                            </select>
                        </div>
                        <div class="input-box">
                            <span class="details">Exchange</span>
                            <select className="selections" value={exchange} onChange={changeExchange}>
                                <option>------Select Exchange------</option>
                                <option value="NSE">NSE</option>
                                <option value="NASDAQ">NASDAQ</option>
                                <option value="LSE">LSE</option>

                            </select><br /> <br />
                        </div>
                    </div><div>
                        <button className="cancelb" onClick={() => navigate("/land")}>Back</button>
                        <button className="nextb" onClick={(event) => savePortfolio(event)}>Next</button>
                    </div>
                </form>
            </div>
        </div>
    );

}

export default PortfolioAdd;