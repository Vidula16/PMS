import React from 'react'
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-enterprise';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import { useState } from 'react';


function GridExamplee() {
    const [gridApi, setGridApi] = useState(null);
    const rows = [{
        "athlete": "Allison schmitt",
        "age": 22,
        "country": "United States",
        "year": 2012,
        "date": "12/03/2012",
        "sport": "swimming",
        "gold": 3,
        "silver": 1,
        "bronze": 1,
        "total": 5
    },
    {
        "athlete": "Natalie Coughin",
        "age": 21,
        "country": "United States",
        "year": 2004,
        "date": "29/08/2004",
        "sport": "swimming",
        "gold": 2,
        "silver": 2,
        "bronze": 1,
        "total": 5
    },
    ]
    const columns = [
        { headerName: "Athlete", field: "athlete",filter:"agTextColumnFilter" },
        { headerName: "Age", field: "age",filter:"agTextColumnFilter" },
        { headerName: "Country", field: "country" ,filter:"agTextColumnFilter"},
        { headerName: "Year", field: "year",filter:"agTextColumnFilter" },
        { headerName: "Date", field: "date" ,filter:"agTextColumnFilter"},
        { headerName: "Sport", field: "sport",filter:"agTextColumnFilter" },
        { headerName: "Gold", field: "gold",filter:"agTextColumnFilter" },
        { headerName: "Silver", field: "silver" ,filter:"agTextColumnFilter"},
        { headerName: "Bronze", field: "bronze",filter:"agTextColumnFilter" },
        { headerName: "Total", field: "total",filter:"agTextColumnFilter" },
    ]

    const datasource = {
        getRows(params) {
            console.log(JSON.stringify(params.request, null, 1));
            const {startRow,filterModel,endRow}=params.request
            let url='http://localhost:4000/olympic?'
            ///Filtering
            const filterKey=Object.keys(filterModel)
            filterKey.forEach(filter=>{
                url+=`${filter}=${filterModel[filter].filter}&`

            })
            ///Pagination
            url+=`_start=${startRow}&_end=${endRow}`
            fetch(url,
                // {
            //     method: 'post',
            //     body: JSON.stringify(params.request),
            //     headers: { 'Content-Type': 'application/json; charset=utf-8' }
            // }
            )
                .then(httpResponse => httpResponse.json())
                .then(response => {
                    params.successCallback(response, 499);
                })
                .catch(error => {
                    console.error(error);
                    params.failCallback();
                })
        }
    };



    const onGridReady = (params) => {
        setGridApi(params);
        // register datasource with the grid
        params.api.setServerSideDatasource(datasource);
    }

    return (
        <div>
            <h1 align="center">AG Grid Practice 2</h1>
            <h4 align="center">NO:1::::Implement Server-side Pagination,Filter and Sorting in AG Grid</h4>
            <div className='ag-theme-alpine-dark' style={{  width: '100% ', textAlign: 'left' }}>
                <AgGridReact
                    rowData={rows}
                    columnDefs={columns}
                    pagination={true}
                    paginationPageSize={8}
                    domLayout="autoHeight"
                    rowModelType='serverSide'
                    onGridReady={onGridReady}
                    defaultColDef={{filter:true,floatingFilter:true}} />

            </div>
        </div>
    )
}

export default GridExamplee
