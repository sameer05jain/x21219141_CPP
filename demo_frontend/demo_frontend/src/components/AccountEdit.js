/* eslint-disable jsx-a11y/anchor-is-valid */
// const AccountEdit = props => {
import './AccountEdit.css';
import { useNavigate } from "react-router-dom";

function AccountEdit(props){
    let history = useNavigate();   
    if(!props.show) {
        return null;
    }
    let accountsData = null;
    const updateAccount = async (event,id) => {
        try {
            let data = {}
            if(document.getElementById("bn" + id).value !== ""){
                data.branchName = document.getElementById("bn" + id).value
            }
            if(document.getElementById("ab" + id).value !== ""){
                data.accountBalance = document.getElementById("ab" + id).value
            }
            if(document.getElementById("type" + id).value !== ""){
                data.accountType = document.getElementById("type" + id).value
            }
            let response = await fetch("http://localhost:8080/accounts/" + id, {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data),
            });
            let jsonResponse = await response.json();
            if(response.ok){
                history("/");
            }else{
                alert(jsonResponse.errorMessage);
            }
        } catch (error) {
            console.log(error)
        }
    }

    const deleteAccount = async (event,id) => {
        try {
            let response = await fetch("http://localhost:8080/accounts/" + id, {
              method: "DELETE",
            });
            if(response.ok){
                history("/");
            }
          } catch (error) {
            console.log(error);
          }
    }

    if(props.data.accounts.length !== 0){
        
        accountsData = 
            props.data.accounts.map(js => (
                <div className='accountUp'>
                <form className="user-account">
                    <input type="branchName" className="form-control inpa" placeholder={js.branchName} id={"bn" + js.accountId}  name="branchName"></input>
                    <input type="text" className="form-control inpa" placeholder={js.accountBalance} id={"ab" + js.accountId} name="accountBalance"></input>
                    <select className="form-control inpa" id={"type" + js.accountId}>
                        <option value="" selected disabled hidden>Select type</option>
                        <option value="S">Savings</option>
                        <option value="C">Current</option>
                    </select>
                    <div className="btn btn-primary inpa" onClick={() => {updateAccount(this,js.accountId)}}>Update</div>
                    <div className="btn btn-danger inpa" onClick={() => {deleteAccount(this,js.accountId)}}>Delete</div>
                </form><br/>
                </div>
            ))
    }else {
        accountsData = <h1>No accounts!</h1>
    }
    
    return (
        <div className="modal">
            <div className='modal-content'>
                <div className='modal-header'>
                    <h4 className='modal-title'>Edit Accounts</h4>
                    <button className="btn btn-warning btnn" onClick={props.onClose}>Close</button>
                </div>
                <div className='modal-body'>
                    
                </div>
                {accountsData}
                <div className='modal-footer'>
                </div>
            </div>
        </div>
    )
}

export default AccountEdit