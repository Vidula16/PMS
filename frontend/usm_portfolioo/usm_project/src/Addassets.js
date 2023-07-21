import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import './Addasset.css';
import axios from "axios";
import PortfolioHeaderService from "./PortfolioHeaderService";


function Addassets() {

    const [assetId, setAssetId] = useState('');
    const [assetClass, setAssetClass] = useState('');
    const [subAssetClass, setSubAssetClasss] = useState('');



    const [investmentHorizon, setInvestmentHorizon] = useState('');
    const [risk, setRisk] = useState('');
    const [liquidity, setLiquidity] = useState('');
    const [returns, setReturns] = useState('');


    const asset_url = "http://localhost:1244/asset/addAsset"
    const handleClick = () => {
        Swal.fire({
            position: 'middle-end',
            icon: 'success',
            title: 'Asset Class Added',
            showConfirmButton: false,
            timer: 2500
        })
    }
    const navigate = useNavigate();

    function addAsset(asset) {
        return axios.post(asset_url, asset)

    }







    const saveAsset = (event) => {
        event.preventDefault()
        const asset = { assetId, assetClass, subAssetClass, risk, investmentHorizon, liquidity, returns }
        console.log(asset);
        addAsset(asset).then(
            (response) => {
                console.log(response.data);


            }).catch(
                error => { console.log(error); });
        if (assetId !== '' && assetClass !== '' && subAssetClass !== '' && risk !== '' && investmentHorizon !== '' && liquidity !== '' && returns !== '') {
            handleClick()
        }
        if (assetId == '' || assetClass == '' || subAssetClass == '' || risk == '' || investmentHorizon == '' || liquidity == '' || returns == '') {
            window.alert("Please fill all the fields")
        }

    }









    return (

        <div>
            <br />
            <div className="containera">
                <div class="headtheme" style={{ "font-size": "190%" }}>ADD ASSET</div>
                <form action="#">
                    <div class="portfolio-details" style={{ marginTop: "-5%" }}>
                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Asset ID</span>
                            <input type="text" style={{ color: "black" }} placeholder="Enter the asset id" required
                                value={assetId} onChange={(event) => setAssetId(event.target.value)}></input>
                        </div>
                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Risk</span>
                            <select className="selections"
                                value={risk} onChange={(event) => setRisk(event.target.value)}>
                                <option>-------Choose Risk-------</option>
                                <option >High</option>

                                <option >Low</option>

                                <option >Medium</option>

                            </select><br /> <br />

                        </div>
                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Asset Class</span>
                            <input type="text" style={{ color: "black" }} placeholder="Enter asset class"
                                value={assetClass} onChange={(event) => setAssetClass(event.target.value)}></input>
                        </div>
                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Returns</span>
                            <select className="selections"
                                value={returns} onChange={(event) => setReturns(event.target.value)}>
                                <option>-----Choose Returns-----</option>
                                <option >High</option>

                                <option >Medium</option>

                                <option >Low</option>

                            </select><br /> <br />
                        </div>
                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Sub-Asset Class</span>
                            <input type="text" style={{ color: "black" }} placeholder="Enter the sub-asset class"
                                value={subAssetClass} onChange={(event) => setSubAssetClasss(event.target.value)}
                            ></input>
                        </div>

                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Investment Horizon</span>
                            <select className="selections"
                                value={investmentHorizon} onChange={(event) => setInvestmentHorizon(event.target.value)}>
                                <option>----Select your choice----</option>
                                <option >Short</option>

                                <option >Long</option>




                            </select><br /> <br />
                        </div>
                        <div class="input-box">
                            <span class="details" style={{ color: "chartreuse", fontWeight: "bold" }}>Liquidity</span>
                            <select className="selections" style={{ width: "78%", "height": "50%" }}
                                value={liquidity} onChange={(event) => setLiquidity(event.target.value)} >
                                <option>----Choose liquidity----</option>
                                <option>High</option>
                                <option>Medium</option>
                                <option>Low</option>

                            </select>
                        </div>
                    </div>
                    <br />
                    <div class="col-md-6 wert">

                        <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Save" onClick={saveAsset} />

                    </div><br /><br />

                    <div class="col-md-6 wert">

                        <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Next" onClick={() => { navigate(`/addtheme`) }} />

                    </div>


                </form>

            </div>
        </div>
    );
}
export default Addassets;
