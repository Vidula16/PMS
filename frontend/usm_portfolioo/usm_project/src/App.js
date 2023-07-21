import Home from './Home';
import './App.css';

import { BrowserRouter, Routes, Route } from "react-router-dom";
import React, { Component } from 'react';
import SecurityPage from './SecurityPage';
import Landing from './Landing';
import Navbar from './Navbar';
import background from './Images/background.png';
import Desktop from './Desktop';
import PortfolioAdd from './PortfolioAdd';
import Addtheme from './Addtheme';
import Addassets from './Addassets';
import Symbol from './Symbol';
import GridExample from './GridExample';
import GridExamplee from './GridExamplee';
import Landingg from './Landingg';
import ThemeModal from './ThemeModal';





function App() {
  return (
    <div className="App" >
      <>
        <Navbar />
      </>
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/home' element={<Home />}></Route>
        <Route path='/addtheme' element={<Addtheme />}></Route>
        <Route path='/addasset' element={<Addassets />}></Route>
        <Route path='/symbol' element={<Symbol />}></Route>
        <Route path='/portfolioadd' element={<PortfolioAdd />}></Route>
        <Route path='/securitypage' element={<SecurityPage />}></Route>
        <Route path='/landing' element={<Landing />}></Route>
        <Route path='/navbar' element={<Navbar />}></Route>
        <Route path='/desktop' element={<Desktop />}></Route>
        <Route path='/grid' element={<GridExample/>}></Route>
        <Route path='/aggrid' element={<GridExamplee/>}></Route>
        <Route path='/land' element={<Landingg/>}></Route>
        {/* <Route path='theme' element={<ThemeModal/>}></Route> */}


      </Routes>

    </div>
  );
}
export default App;
