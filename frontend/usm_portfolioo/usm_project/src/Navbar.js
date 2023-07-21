import React, { useState, useEffect } from "react";
import './Navbar.css';
import symbol from './Images/symbol.png';
function Navbar() {
  return (
    <div>
      <div className="logo">
        <img src={symbol} className="navlogo"></img>
      </div>
      <div className="navcomp">
        <ui class="nav navbar-nav">
          <li><a href="/home" style={{ fontSize: "25px", fontWeight: "bold" }}>Home</a></li>
          <li><a href="/desktop" style={{ fontSize: "25px", fontWeight: "bold" }}>Portfolio</a></li>
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" style={{ "color": "white", fontSize: "25px", fontWeight: "bold" }}>Admin
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" style={{ "backgroundColor": "white", "color": "black" }}>
              <li><a href="/symbol" style={{ fontSize: "18px" }}>Security Master</a></li>
              <li><a href="/addasset" style={{ fontSize: "18px" }}>Add Asset</a></li>
              <li><a href="/addtheme" style={{ fontSize: "18px" }}>Add Theme</a></li>
            </ul>
          </li>
        </ui>
      </div>
      <hr className="line"></hr>
    </div>
  );

}

export default Navbar;