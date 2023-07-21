import React from "react";
import './Addtheme.css'
import ThemeAssetService from "./ThemeAssetService";
import Swal from "sweetalert2";
import axios, { all } from "axios";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

function Addtheme() {

  const navigate = useNavigate();
  const [themeName, setThemeName] = useState('');
  const [assetId, setAssetId] = useState('');
  const [allocation, setAllocation] = useState('');
  const [assetData, setAssetData] = useState('');
  const [themeAssets, setThemeAssets] = useState([])
  const [allocationData, setAllocationData] = useState([]);
  const [calculatedAllocatin, setCalculatedAllocation] = useState('');
  const [existingAllo, setExistingAllo] = useState('');
  const [updatedAllo, setUpdatedAllo] = useState('');

  const theme_url = "http://localhost:1244/themeAsset/addThemeAsset"
  const assetFetch_url = "http://localhost:1244/asset/fetchAsset"
  const fetchAllocationBytheme = "http://localhost:1244/themeAsset/fetchAssetByTheme"


  let temp = 0;








  const handleClick = () => {
    Swal.fire({
      position: 'middle-end',
      icon: 'success',
      title: 'Theme Added',
      showConfirmButton: false,
      timer: 2000
    })
    window.location.reload(false);
  }

  function changethemevalue(e) {
    setThemeName(e.target.value)
    console.log(e.target.value)
    GetAllocationByTheme(e.target.value);

  }

  function Addtheme(assetId, themeAsset) {
    return axios.post(`${theme_url}/` + assetId, themeAsset);
  }

  function fetchAsset() {
    return axios.get(assetFetch_url)
  }

  function getAllocation(themeName) {
    return axios.get(`${fetchAllocationBytheme}/` + themeName)
  }

  console.log(assetId)

  function GetAllocationByTheme(themeName) {
    getAllocation(themeName).then(
      (response) => {
        setAllocationData(response.data)
        console.log(response.data)
      }
    ).catch(error => { console.log(error); })
  }

  useEffect(() => {

    const calthemeAllocation = () => {
      const themeData = allocationData.reduce((accumulator, currentElement) => {
        return accumulator + currentElement.allocation;
      }, 0);

      setExistingAllo(themeData);
    };
    calthemeAllocation();
  }, [allocationData]);



  console.log(allocationData)
  console.log(existingAllo)


  const calAllocation = (event) => {
    setAllocation(event.target.value)
    setCalculatedAllocation(parseInt(existingAllo) + parseInt(event.target.value))

  }
  console.log(existingAllo)
  console.log(allocation)

  console.log(calculatedAllocatin)







  const saveThemeAsset = (event) => {
    if (calculatedAllocatin > 100) {
      window.alert("You Can't Add Allocations above 100%");
      setAllocation("")
      return <Addtheme></Addtheme>
    }
    event.preventDefault()
    const themeAsset = { allocation, themeName, assetId }
    console.log(themeAsset);
    Addtheme(assetId, themeAsset).then(
      (response) => {
        console.log(response.data);


        handleClick()

      }).catch(
        error => { console.log(error); })
  }

  function theme() {

    ThemeAssetService.fetchTheme().then(
      (response) => {
        setThemeAssets(response.data)
        console.log(response.data)
      }
    ).catch(error => { console.log(error); })
  }

  useEffect(() => {
    fetchAsset().then(

      (response) => { setAssetData(response.data) },

    ).catch(error => { console.log(error); })
  }, [])

  useEffect(() => {
    theme()

  }, [])


  console.log(assetData)




  return (

    <div class="container">
      <div class=" text-center mt-5 ">

        <div className="headtheme" style={{ "marginLeft": "-5%", marginTop: "-2%" }}>
          <h1 style={{ fontWeight: "bold" }}>ADD THEME</h1>
        </div>


      </div>
      <div>
        <label className="labelasset" style={{ marginLeft: "-63%" }}>THEME NAME:</label>
        <input className="placeholder" placeholder="Enter the theme name"
          value={themeName} onChange={changethemevalue} style={{
            marginLeft: "1%",
            borderRadius: "4px ",
            border: "2px solid black",
            width: "190px",
            height: "30px", color: "black",
            fontSize: "18px"
          }}></input>

        <div className="assetdiv" style={{ marginLeft: "41%", marginTop: "-3.2%" }}>
          <label className="labelasset" style={{ marginLeft: "-10%" }}>ASSET:</label>
          <select className="placeholder" value={assetId} onChange={(event) => setAssetId(event.target.value)} style={{
            marginLeft: "1%",
            borderRadius: "4px ",
            border: "2px solid black",
            width: "190px",
            height: "30px",
            fontSize: "18px", color: "black"
          }}>
            <option>----choose assset---</option>
            {
              assetData ? (assetData.map((data, key) => (
                <option value={data.assetId}> {data.assetClass}---{data.subAssetClass}</option>
              ))) : (<>Loading...</>)
            }
          </select>

          <label className="labelasset" style={{ marginLeft: "5%" }}>ALLOCATION:</label>
          <input className="placeholder" placeholder="Enter the allocation"
            value={allocation} onChange={calAllocation} style={{
              marginLeft: "1%",
              borderRadius: "4px ",
              border: "2px solid black",
              width: "190px",
              height: "30px", color: "black",
              fontSize: "18px"
            }}></input>


        </div>
        <div class="rowed" style={{ marginTop: "5%", marginLeft: "55%" }}>
          <div class="col-md-6">

            <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Save" style={{ "marginLeft": "-155%" }} onClick={saveThemeAsset} />

          </div>

          <div class="col-md-6">

            <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Cancel" style={{ "marginLeft": "-155%" }} onClick={() => navigate('/desktop')} />

          </div>
        </div>
        <div className="tablediv">
          <table className="table table-bordered">
            <thead style={{ fontSize: "22px", fontWeight: "100", textAlign: "left", "color": "chartreuse" }}>
              <tr>
                <th>Theme Name</th>
                <th>Asset class</th>
                <th>Allocation</th>
              </tr>
            </thead>
            <tbody style={{ fontSize: "18px", textAlign: "left" }}>
              {
                themeAssets.map(themeAsset => <tr key={themeAsset.themeName}>

                  <td>{themeAsset.theme.themeName}</td>
                  <td>{themeAsset.asset.assetClass}</td>
                  <td>{themeAsset.allocation}</td>


                </tr>)}
            </tbody>
          </table>
        </div>
        <div class="col-md-2">

          <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Home" style={{ "marginLeft": "275%", "boxSizing": "border-box" }} onClick={() => navigate('/desktop')} />

        </div>

      </div>



    </div>
  );
}
export default Addtheme;