import React, { useMemo } from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SearchService from "./SearchService";

import symbol from "./Images/symbol.png";

import Graph from "./Images/Graph.jpg";

import sc from "./Images/sc.jpg";

// import stocks from "./Images/stocks.png";

// import'./Symbol.css';
// import Back from "./Images/Back.png"




function Symbol() {

    const navigate = useNavigate();

    const [Symbol, setSymbol] = useState("");

    const [symbolData, setsymbolData] = useState({
        data: '',
        loading: true
    })

    const searchStockById = (e) => {
        // e.preventDefault();
        SearchService.SearchBySymbol(Symbol).then((Response) => {
            console.log(Response.data)
            setsymbolData({
                data: Response.data,
                loading: false
            })

        })

    }

    console.log(symbolData)

    // useEffect(() => {
    //     searchStockById();
    // },[]);


    return (

        <nav>




            {/* <div class="container-fluid" >

                <div class="navbar-header" >

                    <div >

                        <img src={symbol} alt="logo" class="image" />

                    </div>

                </div >




                <div className="list">

                    <ul class="nav navbar-nav" >

                        <li><a class="nav-link" href="#" >Membership</a></li>

                        <li><a class="nav-link" href="#" >Portfolio</a></li>

                        <li><a class="nav-link" href="#" >About</a></li>
                        <li class="dropdown">

                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Search

                                <span class="caret"></span></a>

                            <ul class="dropdown-menu">

                                <li><a href="/Isin">ISIN</a></li>

                                <li><a href="/symbol">Symbol</a></li>

                                <li><a href="/description">Description</a></li>

                                <li><a href="/currency">Currency</a></li>

                                <li><a href="/exchange">Exchange</a></li>

                                <li><a href="/sector">Sector</a></li>

                                <li><a href="/industry">Industry</a></li>

                            </ul>

                        </li>
                        

                        <li><a class="nav-link" href="#" >Product & Services</a></li>

                    </ul>

                </div>




                <div>

                    <button type="button" class="btn btn-success">LOGIN</button>

                </div>

            </div>

            <div>

                <hr className="solid"></hr>

    </div> */}


            <div >

                <div class="row height d-flex justify-content-center align-items-center">

                    <div class="col-md-8">

                        <div class="search">
                            <i class="fa fa-search"></i>
                            <input type="text" class="form-control" value={Symbol} onChange={(e) => setSymbol(e.target.value)} placeholder="search Symbol"></input>
                            <button class="btn btn-primary" onClick={searchStockById} >Search</button>
                        </div>



                    </div>

                </div>


            </div>



            <div>

                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>Symbol</th>
                            <th>ISIN</th>
                            <th>Country</th>
                            <th>Exchange</th>
                            <th>Name Of Company</th>
                            <th>Industry</th>
                            <th>Description</th>

                            <th>Currency</th>
                            <th>Sector</th>
                            <th>Series</th>
                            <th>Last Price</th>
                            {/* <th>Edit</th> */}
                        </tr>
                    </thead>
                    <tbody>
                        {
                            // symbolData.loading ? '' :
                            //     symbolData.data.map(
                            //         symbolData.data =>
                                        <tr key={symbolData.data.symbol}>
                                            <td>{symbolData.data.symbol}</td>
                                            <td>{symbolData.data.isinNumber}</td>
                                            <td>{symbolData.data.country}</td>
                                            <td>{symbolData.data.exchange}</td>

                                            <td>{symbolData.data.nameOfCompany}</td>
                                            <td>{symbolData.data.industry}</td>
                                            <td>{symbolData.data.description}</td>
                                            <td>{symbolData.data.currency}</td>
                                            <td>{symbolData.data.sector}</td>
                                            <td>{symbolData.data.series}</td>
                                            <td>{symbolData.data.lastPrice}</td>
                                            {/* <td><button onClick={() => { navigate("/Update") }}>Update</button> */}
                                                {/* <button>Delete</button> */}
                                            {/* </td> */}
                                        </tr>
                                }</tbody>
                </table>
            </div>




        </nav>

    );

}

export default Symbol;

