import React from 'react'
import axios from 'axios';

function SellModel({closeSellModal,portfolio,securityName,price
    ,onClose
}) {

    console.log(price)
    console.log(portfolio)

    

    const sellsecurity_url="http://localhost:1244/compo/sellSecurity"

    function sellPortfolio(portfolio,securityName) {
        return axios.put(`${sellsecurity_url}/` +portfolio+'/'+ securityName)
    }

    const handlesell = (portfolio,securityName) => {

        sellPortfolio(portfolio,securityName)
        onClose(price)
       window.location.reload(false);

    }

    console.log(securityName)
  return (
    <div>
        <div className="modalBackground" style={{ "paddingBottom": "-5%" }}>
            <div className="modalContainer">
                <div className="title" style={{ "marginTop": "5%" }}>
                    <h1>Are you sure you want to sell?</h1>
                </div>
                <div className="body">
                </div>
                <br />
                <div class="col-md-4">
                    <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Cancel" onClick={() => closeSellModal(false)} style={{ "marginLeft": "125%", "boxSizing": "border-box" }} />
                </div>
                <br />
                <div class="col-md-4">
                    <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="continue" onClick={() => handlesell(portfolio,securityName)} style={{ "boxSizing": "border-box", "marginTop": "15%" }} />
                </div>
            </div>
        </div>

    </div>
  )
}

export default SellModel
