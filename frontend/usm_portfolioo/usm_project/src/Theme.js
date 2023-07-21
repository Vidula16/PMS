import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import './Theme.css';
import axios from "axios";
import ThemeAssetService from "./ThemeAssetService";
import { useLocation } from "react-router-dom";

const Theme = () => {

    const location = useLocation()
    const themeName = location.state.data;
    const navigate = useNavigate();
    const [themeAssets, setThemeAssets] = useState([])
    useEffect(() => {
        ThemeAssetService.fetchThemeAsset(themeName).then(
            (response) => {
                setThemeAssets(response.data)
                console.log(response.data)
            }
        ).catch(error => { console.log(error); })
    }, [])



    return (
        <div>

            <div >
                <button className="theader">THEMES</button>
            </div>
            <div class="container8">
                <table class="table table-bordered">
                    <thead className="head81">
                        <tr>

                            <th>ThemeName</th>
                            <th>Assetclass</th>
                            <th>Allocation</th>
                        </tr>
                    </thead >
                    <tbody className="body81">
                        {
                            themeAssets.map(themeAsset => <tr key={themeAsset.themeName}>

                                <td>{themeAsset.themeAsset.themeName}</td>
                                <td>{themeAsset.assetClass}</td>
                                <td>{themeAsset.allocation}</td>


                            </tr>)}
                    </tbody>
                </table>
                <button className="backt1" onClick={() => navigate('/portfolioadd')}>Back</button>

            </div>
        </div>
    );
}
export default Theme;