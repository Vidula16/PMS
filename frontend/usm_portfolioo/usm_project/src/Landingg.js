import React from 'react';
import add from './Images/add.png';
import { useNavigate, Link, renderMatches } from "react-router-dom";
import './Landing.css';
import { useState, useEffect, useCallback } from "react";
import axios from "axios"
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import edit from './Images/edit.png';
import del from './Images/del.png';
import Modal from "./Modal";
import EditModal from "./EditModal";
import OpenModel from './OpenModel';
import ThemeModal from './ThemeModal';

function Landingg() {

    const navigate = useNavigate();

    const FETCH_URLL = "http://localhost:1244/portfolio/fetchPortfolio"

    const [portfolioData, setportfolioData] = useState([]);
    const [error, setError] = useState(null);
    const [gridApi, setGridAPi] = useState('');
    const [portfolioName, setPortfolioName] = useState('');
    const [editModal, setEditModal] = useState(false);
    const [openModal, setopenModal] = useState(false);
    const [ActiveModel, setActiveModel] = useState(false);
    const [pName, setPortName] = useState('');
    const [data, setData] = useState([]);
    const [themeModal,setThemeModal]=useState(false);
    const [portfolio,setPortfolio]=useState('')

    function getData() {

        return axios.get(`${FETCH_URLL}/`);
    }

    const showModal = (id) => {
        setPortfolioName(id.value)
        setopenModal(true)
        console.log(id.value)
    }
    const showThemeModal = (id) => {
        setPortfolio(id)
        setThemeModal(true)
        console.log(id)
    }
    const showEditModal = (id) => {
        setPortName(id.value)
        setEditModal(true)
        console.log(id.value)
    }
    const showActiveModel = (id) => {
        setPortfolioName(id)
        setActiveModel(true)
        console.log(id)

    }
    const handleCellClicked = (params) => {
        const fieldData = params.data;
        console.log('Field Clicked:', fieldData.portfolioName)
        if (fieldData.status == "Active" || fieldData.status == "New") {
            navigate('/securitypage', { state: { portfoliName: fieldData } })

        } else {
            showActiveModel(fieldData.portfolioName)
        }
    }

    const handlefieldClicked=(params)=>{
        const fieldData=params.data;
        showThemeModal(fieldData.portfolioName)


    }

    const columns = [
        {
            headerName: "Portfolio Name", field: "portfolioName", onCellClicked: handleCellClicked

        },
        {
            headerName: "Fund Manager Name", field: "fundManagerName"
        },
        {
            headerName: "Theme Name", field: "theme.themeName",onCellClicked:handlefieldClicked,
            
        },
        {
            headerName: "BenchMark", field: "benchMark"
        },
        {
            headerName: "Investment Value", field: "investmentValue"
        },
        {
            headerName:"No of securities" , field:"noOfSecurities"
        },
        {
            headerName: "Status", field: "status",

            cellClass: (params) => (params.value == "Active" ? "ActiveStatus" :
                params.value == "Closed" ? "closedStatus" : "newStatus")

        },
        {
            headerName: "Actions", field: "portfolioName",
            cellRenderer: (params) => <div>
                <button className="delbutton" >
                    <img src={del} className="del"
                        onClick={(e) => showModal(params)}></img>
                </button>
                <button className="delbutton" >
                    <img src={edit} className="del"
                        onClick={(e) => showEditModal(params)}></img>
                </button>
            </div>

        }

    ]

    const defaultColDef = {
        sortable: true, filter: true, floatingFilter: true, flex: 1,
        // editable: true
    }

    const onGridReady = params => {
        setGridAPi(params)
        getData().then(
            (response) => { setportfolioData(response.data) }

        ).catch(error => {
            setError(error.message);
        })
    }


    console.log(portfolioData)





    if(error){

        return <div>
            <h1>Oops!Something went wrong while accessing the DB server</h1></div>
        }else{

    return (
        <div>
            <div>
                <button className="addbutton1">
                    <img src={add} className="add1" onClick={() => navigate('/portfolioadd')}></img>
                </button>
            </div>
            <div className="pm">
                <button className="pmbutton">Portfolio Management</button>
            </div>
            <div className='ag-theme-alpine-dark'
                style={{ height: 400, width: '100% ', textAlign: 'left' }}>
                <AgGridReact rowData={portfolioData}
                    columnDefs={columns}
                    defaultColDef={defaultColDef}
                    onGridReady={onGridReady}
                    //rowSelection={rowSelectionType}  //selcting a row without checkbox
                    //onSelectionChanged={onSelectionChanged}
                    //isRowSelectable={isRowSelectable}
                    //onCellClicked={handleCellClicked}
                    // enableBrowserTooltips={true}
                    pagination={true}
                    paginationPageSize={5}
                />
            </div>

            {openModal && <Modal closeModal={setopenModal} portfolioName={portfolioName} />}
            {editModal && <EditModal closeEditModel={setEditModal} portfolioName={pName} />}
            {ActiveModel && <OpenModel closeOpenModel={setActiveModel} portfolioName={portfolioName} />}
            {themeModal && <ThemeModal closeThemeModal={setThemeModal} portfolioName={portfolio} />}
        </div>
    )
}}

export default Landingg
