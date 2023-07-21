import React, { useState, useEffect } from "react";
import Navbar from "./Navbar";
import portfolio from './Images/portfolio.png';
import './Desktop.css';
import BasicExample from "./Navbar";
import { useNavigate } from "react-router-dom";
function Desktop() {

    const navigate = useNavigate();
    return (
        <div className="Desktop-page">

            <div>
                <img src={portfolio} className="name"></img>
            </div>
            <h2>Portfolio Management</h2>
            <div className="para">
                <p>Portfolio management is the art and science of selecting and overseeing a group of investments that meet the long-term financial objectives and risk tolerance of a client, a company, or an institution.</p>
            </div>
            <div className="buttons">
                <button className="createbutton" onClick={() => navigate('/land')}>View Portfolio</button>
            </div>
        </div>
    );
}
export default Desktop;