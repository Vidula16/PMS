import React from 'react';
import './modall.css';
import axios from 'axios';

function OpenModel({closeOpenModel,portfolioName}) {

    const Delete_url = "http://localhost:1244/portfolio/OpenPortfolio"

    console.log(portfolioName.value)

    function deletePortfolio(portfolioName) {
        return axios.delete(`${Delete_url}/` + portfolioName)
    }

    const handleDelete = (portfolioName) => {

        deletePortfolio(portfolioName)
        window.location.reload(false);

    }
  return (
    <div className="modalBackground" style={{ "paddingBottom": "-5%" }}>
    <div className="modalContainer">
        <div className="title" style={{ "marginTop": "5%" }}>
            <h1>Are you sure you want to Active the portfolio?</h1>
        </div>
        <div className="body">
        </div>
        <br />
        <div class="col-md-4">
            <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="continue" onClick={() => handleDelete(portfolioName)} style={{ "marginLeft": "116%", "boxSizing": "border-box"  }} />
        </div>
        <br/>
        <div class="col-md-4">
            <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Cancel" onClick={() => closeOpenModel(false)} style={{ "boxSizing": "border-box", "marginTop": "15%" }} />
        </div>


    </div>
</div>
  )
}

export default OpenModel
