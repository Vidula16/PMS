import React, { useState } from 'react';
import './ThemeModal.css';
import axios from 'axios';
import { useEffect, useCallback } from "react";



function ThemeModal({ closeThemeModal, portfolioName }) {

  const [themeData, setThemedata] = useState([]);
  const [portfolioTheme, setPortfolioTheme] = useState('');
  const theme_url = "http://localhost:1244/portfolio"

  console.log(portfolioName)

  function themecomparison(portfolioName) {
    return axios.get(`${theme_url}/` + portfolioName)
  }

  useEffect(() => {
    themecomparison(portfolioName).then(
      (response) => {
        (setThemedata(response.data))
        setPortfolioTheme(response.data[0])
      },


    ).catch(

      error => { console.log(error); })
  },
    []);

  console.log(themeData)
  console.log(portfolioTheme)


  // const handleDelete = (portfolioName) => {

  //     deletePortfolio(portfolioName)
  //     window.location.reload(false);

  // }

  return (
    <div className="modal-container"
    
    // style={{ "paddingBottom": "-5%" }}
    >
      <div className="modal-lg">
        <div className='heading-tm'>
          <h3> Portfolio Composition By Theme</h3>
        </div>
        <div class="popup-header">

          <div class="attribute">

            <p><strong>Portfolio Name:{portfolioTheme.portfolioName}</strong> </p>

          </div>

          <div class="attribute">

            <p><strong>Invested:{portfolioTheme.investedValue}</strong> </p>

          </div>

          <div class="attribute">

            <p><strong>Theme:{portfolioTheme.themeName}</strong> </p>

          </div>

          <div class="attribute">

            <p><strong>Current:{portfolioTheme.currentValue}</strong> </p>

          </div>

        </div>
        <div class="popup-content">

          <form>

            <table>
              <thead>

                <tr>

                  <th>Asset Class</th>
                  <th>As Per Theme (%)</th>
                  <th>Initial Investment (Rs)*</th>
                  <th>Actual Allocation (%)</th>
                  <th>Current (Rs)</th>
                </tr>

              </thead>
              <tbody>
                {
                  themeData.map(themeDatas => <tr key={themeDatas.portfolioName}>
                    {/* </tr><tr> */}
                    <td>{themeDatas.assetName}</td>
                    <td>{themeDatas.themeAllocation}</td>
                    <td>{themeDatas.initialInvestmentValue}</td>
                    <td>{themeDatas.allocation}</td>
                    <td>{themeDatas.currentAssetValue}</td>
                  </tr>)}
              </tbody>





            </table>

          </form>

        </div>
      </div>
      <div class="col-md-4">
        <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Close" onClick={() => closeThemeModal(false)}
        style={{ "marginLeft": "116%", "boxSizing": "border-box","marginTop":"15px","marginBottom":"15px"}} />
      </div>
      <br />

    </div>

  )
}

export default ThemeModal
