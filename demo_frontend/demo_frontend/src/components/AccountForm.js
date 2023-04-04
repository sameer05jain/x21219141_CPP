import './Main.css'
import { useState,useEffect } from 'react';
import { useNavigate } from "react-router-dom";

function AccountForm() {
    const [user, setUser] = useState("");
    const [branchName, setBranchName] = useState("");
    const [accountBalance, setAccountBalance] = useState("");
    const [accountType, setAccountType] = useState("");
    let history = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            let response = await fetch("http://localhost:8080/accounts", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: user,
                    branchName: branchName,
                    accountBalance: accountBalance,
                    accountType: accountType
                }),
            });
            let jsonResponse = await response.json();
            if(response.ok){
                history("/");
            }else{
                alert(jsonResponse.errorMessage)
            }
            
        } catch (error) {
            console.log(error)
        }
    }

    const [jsx, setJsx] = useState([]);
    const getData = async (event) => {
        try {
            let response = await fetch("http://localhost:8080/users")
            let jsonResponse = await response.json();
            setJsx(jsonResponse);
            console.log(jsx);
        } catch (error) {
            console.log(error)
        }
    }
    useEffect(() => {
        getData()
    }, [])

    return(
        <div className='container Main col-lg-3'>
            <form onSubmit={handleSubmit} className="col-12">
                <div>
                    <div className="form-group row">
                        <label forHTML="user" className="col-sm-3 col-form-label">User: </label>
                        <div class="col-sm-9 item">
                            <select value={user} onChange={ (event) => setUser(event.target.value) } className="form-select" id="user">
                                <option className="form-control" value="">Select user</option>
                                {jsx.map(js => (
                                    <option className="form-control" value={js.userId}>{js.name}</option>
                                ))}
                            </select>
                        </div>
                    </div><br />
                    <div className="form-group row">
                        <label for="branchName" className="col-sm-3 col-form-label">Branch Name: </label>
                        <div class="col-sm-9 item">
                            <input type="branchName" className="form-control" value={branchName} onChange={(event) => setBranchName(event.target.value) } name="branchName"></input>
                        </div>
                    </div><br />
                    <div className="form-group row">
                        <label for="accountBalance" className="col-sm-3 col-form-label">Account Balance: </label>
                        <div class="col-sm-9 item">
                            <input type="text" className="form-control" value={accountBalance} onChange={(event) => setAccountBalance(event.target.value) } name="accountBalance"></input>
                        </div>
                    </div><br />
                    <div className='form-group inline text-center'>
                        <div className="form-check form-check-inline">
                        <input type="radio" className="form-check-input" onChange={(event) => setAccountType(event.target.value)} id="savings" name="accountType" value="S"></input>
                        <label for="savings" className="form-check-label">Savings</label>
                        </div>
                        <div className="form-check form-check-inline ms-3">
                        <input type="radio" className="form-check-input" onChange={(event) => setAccountType(event.target.value)} id="current" name="accountType" value="C"></input>
                        <label for="current" className="form-check-label">Current</label>
                        </div>
                    </div><br />
                    <div className='text-center'>
                        <input type="submit" class="btn btn-primary inp"></input>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default AccountForm;