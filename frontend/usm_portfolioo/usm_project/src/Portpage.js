import React from "react";
import './Portpage.css';
import Swal from "sweetalert2";
function Portpage() {
    return (
        <div className="po">
            <section class="get-in-touch">
                <h1 class="title">PORTFOLIO MANAGEMENT</h1>
                <form class="contact-form row">
                    <div class="form-field col-lg-6">
                        <input id="name" class="input-text js-input" type="text" required />
                        <label class="label" for="name">Portfolio Name</label>
                    </div>
                    <div class="form-field col-lg-6 ">
                        <input id="email" class="input-text js-input" type="email" required />
                        <label class="label" for="email">Fund Manager</label>
                    </div>
                    <div class="form-field col-lg-6">
                        <input id="name" class="input-text js-input" type="text" required />
                        <label class="label" for="name">Invested value</label>
                    </div>
                    <div class="form-field col-lg-6 ">
                        <label class="label" >Currency: </label>&nbsp;
                        <div className="option">
                            <select class="label">
                                <option >INR</option>
                                <option>USD</option>
                                <option>GBP</option>
                            </select><br /></div>
                    </div>
                    <div class="form-field col-lg-6 ">
                        <label class="label" >Theme: </label>&nbsp;
                        <div className="Theme">
                            <select class="label">
                                <option >VERY AGGRESSIVE</option>
                                <option>AGGRESSIVE</option>
                                <option>MODERATIVE</option>
                                <option>CONSERVATIVE</option>
                            </select><br /></div>
                    </div>
                    <div class="form-field col-lg-6 ">
                        <label class="label" >Exchange: </label>&nbsp;
                        <div className="Exchange">
                            <select class="label">
                                <option >NSE</option>
                                <option>LSEG</option>
                                <option>NASDAQ</option>
                            </select><br /></div>
                    </div>
                    <div class="form-field col-lg-6 ">
                        <label class="label" >BENCHMARK: </label>&nbsp;
                        <div className="Benchmark">
                            <select class="label">
                                <option >NIFTY</option>
                                <option>NIFTY50</option>
                                <option>NIFTY100</option>
                            </select><br /></div>
                    </div>
                    <div class="form-field col-lg-6 ">
                        <label class="label" >REBALANCING FREQUENCY: </label>&nbsp;
                        <div className="Rf">
                            <select class="label">
                                <option >DAILY</option>
                                <option>WEEKLY</option>
                                <option>MONTHLY</option>
                                <option>YEARLY</option>
                            </select><br /></div>
                    </div>
                    <div class="form-field col-lg-12">
                        <input class="submit-btn" type="submit" value="Submit" />
                    </div>
                </form></section>
        </div>
    );
}

export default Portpage;