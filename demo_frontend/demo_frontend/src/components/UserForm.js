/* eslint-disable jsx-a11y/anchor-is-valid */
import './Main.css'
import { useState,useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import React from 'react'; 
import AccountEdit from './AccountEdit';   

function UserForm(props) {
    const [items, setItems] = useState({
        name: "",
        email: "",
        mobileNumber: "",
        secondaryNumber: "",
        dob: "",
        show: false
    });
    
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [mobileNo, setMobileNo] = useState("");
    const [secondaryNo, setSecondaryNo] = useState("");
    const [dob, setDob] = useState("");
    const [gender, setGender] = useState("");
    const [type, setType] = useState('text');
    const [show, setShow] = useState(items.show)
    let history = useNavigate();   
    let manageAccounts = null;
    

    const getData = async (event) => {
        try {
            await fetch("http://localhost:8080/users/" + props.id.id)
            .then((response) => response.json())
            .then((json) => {
                setItems(json);
            })
        } catch (error) {
            console.log(error)
        }
    }
    useEffect(() => {
        if(props.id.id){
            getData();
        }
    }, [])

    useEffect(() => {
        setName(items.name)
        setEmail(items.email)
        setMobileNo(items.mobileNumber)
        setSecondaryNo(items.secondaryNumber)
        setDob(items.dob)
        setGender(items.gender)
    }, [items.name,items.email,items.mobileNumber,items.secondaryNumber,items.dob,items.gender]);

    const formValidation =() => {
        if(name === ""){
            alert("Name cannot be empty");
            return false;
        }
        if(email === ""){
            alert("Email cannot be empty");
            return false;
        }
        if(mobileNo === ""){
            alert("Mobile number cannot be empty");
            return false;
        }
        if(mobileNo.length !== 10){
            alert("Mobile number invalid");
            return false;
        }
        if(gender === ""){
            alert("Please select gender");
            return false;
        }
        return true;
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            if(props.id.id){
                let data = {}
                if(name !== ""){
                    data.name = name;
                }
                if(email !== ""){
                    data.email = email;
                }
                if(mobileNo !== ""){
                    data.mobileNumber = mobileNo
                }
                if(secondaryNo !== ""){
                    data.secondaryNumber = secondaryNo
                }
                if(dob !== ""){
                    data.dob = dob
                }
                if(gender !== ""){
                    data.gender = gender
                }
                let response = await fetch("http://localhost:8080/users/" + props.id.id, {
                    method: "PUT",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)    
                });
                let jsonResponse = await response.json();
                if(response.ok){
                    history("/");
                }else{
                    alert(jsonResponse.errorMessage);
                }
            }else{
                if(formValidation()){
                    let response = await fetch("http://localhost:8080/users", {
                        method: "POST",
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            name: name,
                            email: email,
                            mobileNumber: mobileNo,
                            secondaryNumber: secondaryNo,
                            dob: dob,
                            gender: gender
                        }),
                    });
                    let jsonResponse = await response.json();
                    if(response.ok){
                        history("/");
                    }else{
                        alert(jsonResponse.errorMessage);
                    }
                }
            }
        } catch (error) {
            console.log(error)
        }
        
    }
    let genderSelect = null;
    if(props.id.id){
        manageAccounts = <><a href="#" className="btn btn-danger ma" onClick={() => { setShow(true)}}>Manage Accounts</a>
                         <AccountEdit onClose={() => { setShow(false)}} show={show} data={items}/></>
    }else {
        genderSelect = (
            <>
                <div className='form-group inline text-center'>
                            <div className="form-check form-check-inline">
                                <input type="radio" required className="form-check-input" onChange={(event) => setGender(event.target.value)} id="male" name="gender" value="M"></input>
                                <label htmlFor="male" className="form-check-label">Male</label>
                            </div>
                            <div className="form-check form-check-inline ms-3">
                                <input type="radio" className="form-check-input" onChange={(event) => setGender(event.target.value)} id="female" name="gender" value="F"></input>
                                <label htmlFor="female" className="form-check-label">Female</label>
                            </div>
                </div><br/>
            </>
        )
    }
    
    return(
        <div className='container Main col-lg-3'>
            <form className="col-12">
                <div>
                    <div className="form-group row">
                        <div className="col-sm-12 item">
                            <input type="text" placeholder="Name" required className="form-control" value={name} onChange={(event) => setName(event.target.value)} name="name"></input>
                        </div>
                    </div><br/>
                    <div className="form-group row">
                        <div className="col-sm-12 item">
                            <input type="email" placeholder="Email" required className="form-control" value={email} onChange={(event) => setEmail(event.target.value) } name="email"></input>
                        </div>
                    </div><br/>
                    <div className="form-group row">
                        <div className="col-sm-12 item">
                            <input type="number" placeholder="Mobile number" required className="form-control" value={mobileNo} onChange={(event) => setMobileNo(event.target.value) } name="mobileNo"></input>
                        </div>
                    </div><br/>
                    <div className="form-group row">
                        <div className="col-sm-12 item">
                            <input type="number" placeholder="Secondary number" required className="form-control" value={secondaryNo} onChange={(event) => setSecondaryNo(event.target.value) } name="secondaryNo"></input>
                        </div>
                    </div><br/>
                    <div className="form-group row">
                        <div className="col-sm-12 item">
                            <input type={type} placeholder="Date of birth" required onFocus={() => setType('date')} onBlur={() => setType('text')} className="form-control" value={dob} onChange={(event) => setDob(event.target.value) } name="dob"></input>
                        </div>
                    </div><br/>
                    {genderSelect}
                        <div className='buttons'>
                            <a href="#" className="btn btn-primary inp" onClick={handleSubmit}>Update</a>
                            {manageAccounts}
                        </div>
                </div>
            </form>
        </div>
    );    
}

export default UserForm;