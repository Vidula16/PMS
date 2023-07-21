import React from 'react'
import {AgGridReact} from 'ag-grid-react';
import 'ag-grid-community/styles/ag-grid.css';
import 'ag-grid-community/styles/ag-theme-alpine.css';
import { useState ,useEffect,useCallback} from 'react';
import axios from 'axios';
import './GridExample.css';

function GridExample() {

    const FETCH_URLL = "http://localhost:1244/portfolio/fetchPortfolio"

    const [portfolioData, setportfolioData] = useState([]);
    const [error,setError]=useState(null);
    const [gridApi,setGridAPi]=useState(null);
    const [gridColumnApi,setGridColumnApi]=useState(null);
    const [hideColumn,setHideColumn]=useState(false);


    function getData() {

        return axios.get(`${FETCH_URLL}/`);
    }

    // useEffect(() => {
    //     getData().then(
    //         (response) => { setportfolioData(response.data) },
    //     ).catch(error => {
    //         setError(error.message); })
    // }, []);

    console.log(portfolioData)

const actionButton=(params)=>{
        console.log(params);
        alert(`${params.data.portfolioName}  ${params.data.status}`)
}

    const columns=[
        {
            headerName:"Portfolio Name",field:"portfolioName",
            checkboxSelection:true,  //checkbox in everycoloumn
            heaerCheckboxSelection:true, //header checkbox to select all entries
            tooltipField:"status" //hover effecr to display status
        },
        {
            headerName:"Fund Manager Name",field:"fundManagerName"
        },
        {
            headerName:"Theme Name",field:"theme.themeName"
        },
        {
            headerName:"BenchMark",field:"benchMark"
        },
        {
            headerName:"Investment Value",field:"investmentValue"
        },
        {
            headerName:"Status",field:"status",
            // cellStyle:(params)=>(params.value=="Active" ? {background:"green"}:
            // params.value=="Closed"?{background:"red"}:{borderLeft:"4px yellow solid"})
            cellClass:(params)=>(params.value=="Active" ? "ActiveStatus":
            params.value=="Closed"?"closedStatus":"newStatus")

            //cellStyle apply css in giving in that line itself
            //cellClass apply css by className from css file

        },
        {
            headerName:"Actions",field:"portfolioName",
        //    hide:true, //to  hide the column at first
            colId: "actionCol", //to hit this column using this colid
            cellRenderer:(params)=><div>
                <button onClick={()=>actionButton(params)}>Click Me</button>
            </div>
        }
    ]



    ///// --------------FOR SOARTING,EDITABLE,FILTERING,FLOATING FILTER-------/////////////

    const defaultColDef={
        sortable:true,
        editable:true,
        filter:true,
        // floatingFilter:true,
        flex:1
    }

    //let gridApi;
    const onGridReady=params=>{
        setGridAPi(params.api);
        setGridColumnApi(params.columnApi);
    
        getData().then(
                     (response) => { setportfolioData(response.data)}

             ).catch(error => {
                     setError(error.message); })
             }



//to download file as csv file
    const onExportClick = useCallback(() =>{
gridApi.exportDataAsCsv();
    }, []);

// row selction without checkbox
//const rowSelectionType='single'; //for single row selection
const rowSelectionType='multiple'; //for multiple row selection

//row selected event
const onSelectionChanged=(event)=>{
   console.log(event) //to display cpmolete api details
   console.log(event.api.getSelectedRows())  //todisplay selected row details
}

//select row based on condition
const isRowSelectable=(node)=>{
return node.data?node.data.status=="Active" ||node.data.status=="New" :false
}

//to show the hidden column
const showColumn=()=>{
    
    gridColumnApi.setColumnVisible('actionCol',hideColumn)

    console.log(!hideColumn)
    setHideColumn(!hideColumn)   
    
}

///Common search from all fields
const onFilterTextChange=(e)=>{
     console.log(e.target.value)
     gridApi.setQuickFilter(e.target.value)
}

// const hideeColumn=()=>{
    
//     gridColumnApi.setColumnVisible('actionCol',false)

//     //console.log(!hideColumn)
//     // setHideColumn(!hideColumn)


    
    
// }





  return (
    <div>
        <input type="search" onChange={onFilterTextChange} placeholder='search something' style={{color:"black"}}></input>
        <h1></h1>
        
<div>
<button onClick={()=>onExportClick()} style={{color:"black"}}>export</button>
<button onClick={()=>showColumn()} style={{color:"black"}}>show Actions</button>
{/* <button onClick={()=>hideColumn()} style={{color:"black"}}>hide column</button> */}
<div className='ag-theme-alpine-dark'
style={{height:400,width:'100% ',textAlign:'left'}}>
    <AgGridReact rowData={portfolioData}
    columnDefs={columns}
    defaultColDef={defaultColDef}   //To float,editable,sorting in column field
    onGridReady={onGridReady}
    enableBrowserTooltips={true}  //to display status  on hoverof portfolio name
    tooltipShowDelay={{tooltipShowDelay:2}}  //todelay the tool tip by 2 secs
    rowSelection={rowSelectionType}  //selcting a row without checkbox
    onSelectionChanged={onSelectionChanged} //after selecting an row,to do an any function
    rowMultiSelectWithClick={true}  //select multiple rows without ctrl+click
    isRowSelectable={isRowSelectable}  //select row based on condition
    pagination={true}  //To enable Pagination
    paginationPageSize={5} //To set size of page
    //paginationAutoPageSize={true} Autp set Page Size

    />
</div>
</div>
    </div>
  )
}

export default GridExample
