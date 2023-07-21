import React, { useState } from 'react'
import axios from 'axios';
import './EditModal.css';

function EditModal({ closeEditModel, portfolioName }) {
    const [investmentValue, setInvestmentValue] = useState('');
    const Update_url = "http://localhost:1244/portfolio/update"


    function updateValue(portfolioName, investmentValue) {
        return axios.put(`${Update_url}/` + portfolioName + `/` + investmentValue)
    }

    const handleSave = (portfolioName, investmentValue) => {

        updateValue(portfolioName, investmentValue).then(
            (response) => {
                console.log(response.data);
                window.location.reload(false)
            }).catch(
                error => { console.log(error); })
    }



    return (
        <div className="modalBackground">
            <div className="modalContainer">

                <div className="titleem">
                    <h1>UPDATE INVESTMENT VALUE</h1>
                </div>
                <div className="bodyem">
                    <form>
                        <label style={{ fontSize: "200%", marginTop: "2%" }}>PORTFOLIO NAME: {portfolioName}</label>
                        <br />
                        <label style={{ fontSize: "150%", marginTop: "2%" }}>INVESTMENT VALUE :</label><br />
                        <input style={{ width: "43%", height: "60%", fontSize: "150%" }} placeholder="    investement value" type="text" value={investmentValue} onChange={(event) => setInvestmentValue(event.target.value)}></input>

                    </form>

                </div>
                <br />
                <br />
                <div className="footer" style={{marginTop:"-4%"}}>
                    <button className='cancelm' onClick={() => closeEditModel(false)}>Cancel</button>
                    <button className='savem' onClick={() => handleSave(portfolioName, investmentValue)}>save</button>
                </div>
            </div>

        </div>
    )
}

export default EditModal
