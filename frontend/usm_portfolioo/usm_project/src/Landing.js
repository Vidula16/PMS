import React from "react";
import './Landing.css';
import {AgGridReact} from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import Navbar from "./Navbar";
import edit from './Images/edit.png';
import del from './Images/del.png';
import add from './Images/add.png';
import { useNavigate, Link, renderMatches } from "react-router-dom";
import { useState, useEffect ,useCallback} from "react";
import axios from "axios"
import Modal from "./Modal";
import EditModal from "./EditModal";


const Landing = () => {
    const navigate = useNavigate();
    const FETCH_URLL = "http://localhost:1244/portfolio/fetchPortfolio"

    const [portfolioData, setportfolioData] = useState([]);
    const [linkEnabled, setlinkEnabled] = useState(true);
    const [openModal, setopenModal] = useState(false);
    const [portfolioName, setportfolioName] = useState('');
    const [editModal, setEditModal] = useState(false);
    const [pName, setPortName] = useState('');
    const [error,setError]=useState(null);


    function getData() {

        return axios.get(`${FETCH_URLL}/`);
    }

    const showModal = (id) => {
        setportfolioName(id)
        setopenModal(true)
        console.log(id)
    }

    console.log(portfolioName)

    const showEditModal = (id) => {
        setPortName(id)
        setEditModal(true)
        console.log(id)
    }

    useEffect(() => {
        getData().then(
            (response) => { setportfolioData(response.data) },
        ).catch(error => {
            setError(error.message); })
    }, []);


    console.log(error)



    // useEffect(() => {
    //     try{

    //         getData().then((response)=>
    //         {
    //             if(!response.ok){
    //                 throw new Error('Error Fetching data from server')
    //             }

    //             setportfolioData(response.data)

    //         },)

    //     }catch(error){
    //         setError(error.message)
    //     }
    // })

    console.log(portfolioData)

if(error){

return <div>
    <h1>Oops!Something went wrong while accessing the DB server</h1></div>
}else{
    return (
        <div>

            <div >
                <div>
                    <button className="addbutton1">
                        <img src={add} className="add1" onClick={() => navigate('/portfolioadd')}></img>
                    </button>
                </div>
                <div className="pm">
                    <button className="pmbutton">Portfolio Management</button>
                </div>
                <div>
                    <div class="container">
                        <table class="table table-bordered">
                            <thead className="header1">
                                <tr>
                                    <th>Portfolio Name</th>
                                    <th>Fund Manager</th>
                                    <th>Theme Name</th>
                                    <th>Benchmark</th>
                                    <th>Invested Value</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody className="body1">
                                {portfolioData.map(portfolioDatas => <tr key={portfolioDatas.portfolioName}>
                                    <td>
                                        {/* portfolioDatas.linkEnabled ? ( */}
                                        <Link to="/securitypage" state={{ portfoliName: portfolioDatas.portfolioName }}>{portfolioDatas.portfolioName}</Link>
                                        {/* ) : ('') */}
                                    </td>
                                    <td>{portfolioDatas.fundManagerName}</td>
                                    <td>{portfolioDatas.theme.themeName}</td>
                                    <td>{portfolioDatas.benchMark}</td>
                                    <td>{portfolioDatas.investmentValue}</td>
                                    <td>{portfolioDatas.status}</td>
                                    <td>
                                        <button className="delbutton" >
                                            <img src={del} className="del"
                                                onClick={(e) => showModal(portfolioDatas.portfolioName)}></img>
                                        </button>
                                        <button className="delbutton" >
                                            <img src={edit} className="del"
                                                onClick={(e) => showEditModal(portfolioDatas.portfolioName)}></img>
                                        </button>
                                    </td>
                                </tr>)
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
                {openModal && <Modal closeModal={setopenModal} portfolioName={portfolioName} />}
                {editModal && <EditModal closeEditModel={setEditModal} portfolioName={pName} />}
                <div class="col-md-2">
                    <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Back" style={{ marginLeft: "278%", marginTop: "100%" }} onClick={() => navigate('/desktop')} />
                </div>
            </div>
        </div>
    );
}}
export default Landing;