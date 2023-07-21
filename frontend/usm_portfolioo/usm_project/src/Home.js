import React from "react";
import symbol from "./Images/symbol.png";
import stocks from "./Images/stocks.png";
import './Home.css';
import Navbar from "./Navbar";


function Home() {

    return (
        <div>

            <div>
                <img src={stocks} className="mainname"></img>
            </div>
            <h2>Invest In Stocks</h2>
            <div className="mainpara">
                <p>Investing in stocks means buying shares of ownership in a public company. Those shares are called stock. If a stock you own becomes more valuable, you could earn a profit if you decide to sell it to another investor. Most people invest in stocks online, through a brokerage account. Trading is something that is not for everyone but everyone can try it once to be successful.</p>
            </div>

        </div>
    );

}

export default Home;